package com.smallproject.domain.service;

import com.smallproject.ui.util.Url;
import java.io.InputStream;
import javax.inject.Inject;
import okhttp3.OkHttpClient;

public class ChatsService extends AbstractService {

  @Inject public ChatsService(OkHttpClient okHttpClient) {
    super(okHttpClient);
  }

  public InputStream getChats() throws Exception{
    return get(Url.SAMPLE_JSON);
  }

}
