package com.smallproject.domain.service;

import java.io.IOException;
import java.io.InputStream;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class AbstractService {

  private OkHttpClient okHttpClient;

  public AbstractService(OkHttpClient okHttpClient) {
    this.okHttpClient = okHttpClient;
  }

  public InputStream get(String url) throws IOException {
    Request request = new Request.Builder()
        .url(url)
        .build();
    return okHttpClient.newCall(request).execute().body().byteStream();
  }

}
