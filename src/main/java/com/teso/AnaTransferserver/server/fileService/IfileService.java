package com.teso.AnaTransferserver.server.fileService;

import java.io.InputStream;

public interface IfileService {
 void saveFile(InputStream inputStream,String path);

}
