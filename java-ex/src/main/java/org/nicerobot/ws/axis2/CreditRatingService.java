package org.nicerobot.ws.axis2;

import java.util.concurrent.Future;

import javax.jws.WebService;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;
import javax.xml.ws.soap.Addressing;

@Addressing
@WebService
public interface CreditRatingService {
  // sync operation
  Score getCreditScore (Customer customer);

  // async operation with polling
  Response<Score> getCreditScoreAsync (Customer customer);

  // async operation with callback
  Future<?> getCreditScoreAsync (Customer customer, AsyncHandler<Score> handler);
}
