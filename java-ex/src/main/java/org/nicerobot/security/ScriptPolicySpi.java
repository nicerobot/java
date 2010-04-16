/*
 * Copyright 2010 Nice Robot Corporation
 * http://nicerobot.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 *
 */
/**
 * 
 */

package org.nicerobot.security;

import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.PolicySpi;
import java.security.ProtectionDomain;

import org.nicerobot.io.Sync;

/**
 * @author nicerobot
 * 
 */
public class ScriptPolicySpi extends PolicySpi {

	static {
		Sync.out.stackTrace(new Throwable("ScriptPolicySpi class initialization"));
	}

	/**
	 * 
	 */
	public ScriptPolicySpi () {
		super();
		Sync.out.stackTrace(new Throwable("ScriptPolicySpi constructor"));
	}

	/* (non-Javadoc)
	 * @see java.security.PolicySpi#engineGetPermissions(java.security.CodeSource)
	 */
	@Override
	protected PermissionCollection engineGetPermissions (final CodeSource codesource_) {
		Sync.out.format("engineGet");
		return super.engineGetPermissions(codesource_);
	}

	/* (non-Javadoc)
	 * @see java.security.PolicySpi#engineGetPermissions(java.security.ProtectionDomain)
	 */
	@Override
	protected PermissionCollection engineGetPermissions (final ProtectionDomain domain_) {
		Sync.out.format("engineGet");
		return super.engineGetPermissions(domain_);
	}

	/* (non-Javadoc)
	 * @see java.security.PolicySpi#engineImplies(java.security.ProtectionDomain, java.security.Permission)
	 */
	@Override
	protected boolean engineImplies (final ProtectionDomain domain_, final Permission permission_) {
		Sync.out.format("engineImplies");
		return false;
	}

	/* (non-Javadoc)
	 * @see java.security.PolicySpi#engineRefresh()
	 */
	@Override
	protected void engineRefresh () {
		Sync.out.format("engineRefresh");
		super.engineRefresh();
	}

}
