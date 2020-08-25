package com.teso.AnaTransferserver.server.fileService;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Service
public class FileServiceImp implements IfileService {

    @Override
    public void saveFile(InputStream inputStream, String path) {
        try {
            OutputStream outputStream = new FileOutputStream(new File(path));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
