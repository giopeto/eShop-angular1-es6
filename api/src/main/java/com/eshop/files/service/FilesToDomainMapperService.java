package com.eshop.files.service;

import com.eshop.files.domain.FilesToDomainMapper;

public interface FilesToDomainMapperService {

	public FilesToDomainMapper save(FilesToDomainMapper filesToDomainMapper);
	public FilesToDomainMapper getByDomainId(String domainId);
	public FilesToDomainMapper addFileId(String domainId, String fileId);
	public void deleteByDomainId(String domainId);
	public void removeFileByFileId(String domainId, String fileId);
}
