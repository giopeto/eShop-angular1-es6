package com.eshop.files.service;

import com.eshop.files.domain.FilesToDomainMapper;
import com.eshop.files.domain.FilesUpload;
import com.mongodb.gridfs.GridFSDBFile;

import java.util.List;

public interface FilesService {

	public FilesToDomainMapper store(FilesUpload filesUpload);
	public GridFSDBFile getById(String id);
	public void delete(String id, String domainId);
	public void delete(List<String> ids, String domainId);
}
