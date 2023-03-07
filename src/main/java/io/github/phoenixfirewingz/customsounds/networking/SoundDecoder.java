package io.github.phoenixfirewingz.customsounds.networking;

import org.apache.http.HttpException;
import org.apache.http.client.HttpResponseException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SoundDecoder {

    private class SoundData
    {
        public final HttpURLConnection httpConn;
        public final InputStream data;
        public SoundData(HttpURLConnection httpCon,InputStream data)
        {
            this.httpConn = httpCon;
            this.data = data;
        }

    }
    SoundDecoder(SoundTypes type,String url) throws Exception
    {
        SoundData data = downloadFile(url);

        data.data.close();
        data.httpConn.disconnect();
    }

    private SoundData downloadFile(String fileURL) throws Exception {
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = httpConn.getInputStream();
            return new SoundData(httpConn,inputStream);
        }
        throw new HttpResponseException(httpConn.getResponseCode(),"was not the expected resonance");
    }
}
