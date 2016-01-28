/*
* Copyright (C) 2015 Pedro Paulo de Amorim
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
*/
package com.smallproject.domain.service;

import com.smallproject.ui.util.Url;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Inject;
import okhttp3.OkHttpClient;

public class PostService extends AbstractService {

  @Inject public PostService(OkHttpClient okHttpClient) {
    super(okHttpClient);
  }

  public InputStream getRecentPosts() throws IOException {
      return get(Url.SAMPLE_JSON);
  }

}
