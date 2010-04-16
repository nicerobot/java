package org.nicerobot.security;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.KeyStore.SecretKeyEntry;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

public class Keys {

  private static final KeyStore _keystore;
  private static final SecretKeyFactory _skeyfactory;
  private static final char[] _namec;

  static {
    KeyStore ks = null;
    try {
      ks = KeyStore.getInstance("JCEKS", "SunJCE");
    } catch (final KeyStoreException e) {
      e.printStackTrace();
    } catch (final NoSuchProviderException e) {
      e.printStackTrace();
    }
    _keystore = ks;

    SecretKeyFactory skf = null;
    try {
      skf = SecretKeyFactory.getInstance("PBEWithSHA1AndDESede");
    } catch (final NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    _skeyfactory = skf;

    String name = null;
    try {
      name = String.format("%s/%s", InetAddress.getLocalHost(), System.getProperty("user.name"));
    } catch (final UnknownHostException e) {
      e.printStackTrace();
    }
    _namec = name.toCharArray();

  }

  /**
   * @param alias
   * @return
   */
  private static char[] passwordPrompt (final String alias) {
    final JPasswordField jpf = new JPasswordField();
    final JOptionPane jop =
        new JOptionPane(jpf, JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
    final JDialog dialog = jop.createDialog("Password for " + alias + ":");
    dialog.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentShown (final ComponentEvent e) {
        SwingUtilities.invokeLater(new Runnable() {
          @Override
          public void run () {
            jpf.requestFocusInWindow();
          }
        });
      }
    });
    jpf.requestFocus();
    dialog.setVisible(true);
    final int result = (Integer) jop.getValue();
    dialog.dispose();
    char[] password = null;
    if (result == JOptionPane.OK_OPTION) {
      password = jpf.getPassword();
    }
    return password;
  }

  private final File _root;

  private final File _store;

  public Keys (final String keystore) throws NoSuchAlgorithmException, CertificateException,
      IOException {

    File root = new File(keystore);
    if (!root.isAbsolute()) {
      root = new File(String.format("%s/.%s", System.getProperty("user.home"), keystore));
    }
    this._store = root;
    root = root.getParentFile();
    this._root = root;

    if (this._root.exists() && !this._root.isDirectory()) {
      throw new RuntimeException(String.format("%s is not a directory",
                                               this._root.getAbsolutePath()));
    }
    if (!this._root.exists()) {
      if (!this._root.mkdirs()) {
        throw new RuntimeException(String.format("Can not create %s directory",
                                                 this._root.getAbsolutePath()));
      }
    }
    this._root.setReadable(false, false);
    this._root.setExecutable(false, false);
    this._root.setWritable(false, false);
    this._root.setReadable(true, true);
    this._root.setWritable(true, true);
    this._root.setExecutable(true, true);

    if (this._store.exists()) {
      final FileInputStream fis = new FileInputStream(this._store);
      _keystore.load(fis, _namec);
      fis.close();
    } else {
      _keystore.load(null, null);
    }

  }

  /**
   * @param secured
   * @throws KeyStoreException
   * @throws NoSuchProviderException
   * @throws NoSuchAlgorithmException
   * @throws CertificateException
   * @throws IOException
   */
  public synchronized char[] getPassword (final String alias) throws KeyStoreException,
      NoSuchProviderException, NoSuchAlgorithmException, CertificateException, IOException {

    if (!_keystore.entryInstanceOf(alias, KeyStore.SecretKeyEntry.class)) {
      return null;
    }

    try {
      final KeyStore.PasswordProtection kp = new KeyStore.PasswordProtection(_namec);
      final KeyStore.SecretKeyEntry ent = (SecretKeyEntry) _keystore.getEntry(alias, kp);
      final SecretKey sk = ent.getSecretKey();
      final PBEKeySpec key = (PBEKeySpec) _skeyfactory.getKeySpec(sk, PBEKeySpec.class);
      return key.getPassword();
    } catch (final Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  /**
   * @param file
   * @throws InvalidKeySpecException
   * @throws KeyStoreException
   * @throws IOException
   * @throws CertificateException
   * @throws NoSuchAlgorithmException
   */
  public synchronized void setPassword (final String alias) throws InvalidKeySpecException,
      KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {

    final KeyStore.PasswordProtection kp = new KeyStore.PasswordProtection(_namec);
    final SecretKey sk = _skeyfactory.generateSecret(new PBEKeySpec(passwordPrompt(alias)));
    final KeyStore.SecretKeyEntry skEntry = new KeyStore.SecretKeyEntry(sk);
    _keystore.setEntry(alias, skEntry, kp);
    final FileOutputStream fos = new FileOutputStream(this._store);
    _keystore.store(fos, _namec);
    fos.close();

    this._store.setReadable(false, false);
    this._store.setExecutable(false, false);
    this._store.setWritable(false, false);
    this._store.setReadable(true, true);
    this._store.setWritable(true, true);

  }

}
