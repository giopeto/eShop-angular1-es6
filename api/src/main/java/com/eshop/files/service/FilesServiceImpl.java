package com.eshop.files.service;

import com.eshop.files.domain.FilesToDomainMapper;
import com.eshop.files.domain.FilesUpload;
import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.GridFSDBFile;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@Slf4j
public class FilesServiceImpl implements FilesService {

	@NonNull private final GridFsOperations gridFsOperations;
	@NonNull private final FilesToDomainMapperService filesToDomainMapperService;

	@Autowired
	public FilesServiceImpl(GridFsOperations gridFsOperations, FilesToDomainMapperService filesToDomainMapperService) {
		this.gridFsOperations = gridFsOperations;
		this.filesToDomainMapperService = filesToDomainMapperService;
	}

	@Override
	public FilesToDomainMapper store(FilesUpload filesUpload) {
		filesUpload.getFiles().forEach(file -> {
			try(InputStream inputStream = file.getInputStream()) {
				filesToDomainMapperService.addFileId(filesUpload.getDomainId(),
					gridFsOperations.store(inputStream, file.getOriginalFilename(), file.getContentType(), new BasicDBObject()).getId().toString());
			} catch (IOException e) {
				log.error("Error while storing file with name {}: {}", file.getOriginalFilename(), e.getMessage());
			}
		});

		return filesToDomainMapperService.getByDomainId(filesUpload.getDomainId());
	}

	public GridFSDBFile getById(String id) {
		GridFSDBFile gridFile = gridFsOperations.findOne(new Query(GridFsCriteria.where("_id").is(id)));
		return gridFile;
	}

	@Override
	public void delete(String id, String domainId) {
		gridFsOperations.delete(new Query(GridFsCriteria.where("_id").is(id)));
		filesToDomainMapperService.removeFileByFileId(domainId, id);
	}

	@Override
	public void delete(List<String> ids, String domainId) {
		gridFsOperations.delete(new Query(GridFsCriteria.where("_id").is(ids)));
	}
}
