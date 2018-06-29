package com.eshop.files.controller;

import com.eshop.files.domain.FilesToDomainMapper;
import com.eshop.files.domain.FilesUpload;
import com.eshop.files.service.FilesService;
import com.eshop.files.service.FilesToDomainMapperService;
import com.mongodb.gridfs.GridFSDBFile;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.eshop.common.ApiConstants.API_BASE_URL;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(API_BASE_URL + "/files")
@CrossOrigin
public class FilesController {

	@NonNull private final FilesService filesService;
	@NonNull private final FilesToDomainMapperService filesToDomainMapperService;

	@Autowired
	public FilesController(FilesService filesService, FilesToDomainMapperService filesToDomainMapperService) {
		this.filesService = filesService;
		this.filesToDomainMapperService = filesToDomainMapperService;
	}

	@RequestMapping(method = POST)
	public FilesToDomainMapper save(@ModelAttribute FilesUpload filesUpload) throws IOException {
		return filesService.store(filesUpload);
	}

	@RequestMapping(value = "{domainId}", method = GET)
	public FilesToDomainMapper getByDomainId(@PathVariable String domainId) {
		return filesToDomainMapperService.getByDomainId(domainId);
	}

	@RequestMapping(value = "file/{fileId}", method = GET)
	public ResponseEntity<InputStreamResource> getById(@PathVariable String fileId) {
		GridFSDBFile gridFsFile = filesService.getById(fileId);

		return ResponseEntity.ok()
			.contentLength(gridFsFile.getLength())
			.contentType(MediaType.parseMediaType(gridFsFile.getContentType()))
			.body(new InputStreamResource(gridFsFile.getInputStream()));
	}

	@RequestMapping(value = "{id}/domainId/{domainId}", method = DELETE)
	public void delete(@PathVariable String id, @PathVariable String domainId) {
		filesService.delete(id, domainId);
	}
}
