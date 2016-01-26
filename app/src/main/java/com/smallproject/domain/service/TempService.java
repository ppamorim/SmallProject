package com.smallproject.domain.service;

import android.location.Location;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Inject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TempService {

  private OkHttpClient okHttpClient;

  @Inject public TempService(OkHttpClient okHttpClient) {
    this.okHttpClient = okHttpClient;
  }

  public InputStream getActualTemperature(Location location) throws IOException {
      Request request = new Request.Builder()
          .url("")
          .build();
      Response response = okHttpClient.newCall(request).execute();
      return response.body().byteStream();
  }

}
