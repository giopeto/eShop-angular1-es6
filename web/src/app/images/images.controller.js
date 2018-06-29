import imagesService from './images.service';

export default class ImagesController {

	constructor($scope, $state, $http, filesToDomainMapper, domainId) {
		this.$scope = $scope;
		this.$state = $state;
		this.$http = $http;
		this.filesToDomainMapper = filesToDomainMapper;
		this.domainId = domainId;
		this.filesForUpload = {files: [], src: []};
		this.addEventListenerToFileInput(this.filesForUpload, this.$scope);
		this.IMAGES_URL = imagesService.getImagesUrl();
	}
		
	save(formData) {
		return Api.callMulty(this.IMAGES_URL, formData);
	}

	uploadFiles() {
		imagesService.save(this.domainId).then(response => {
			this.filesToDomainMapper = response;
			this.filesForUpload.files.length = 0;
			this.filesForUpload.src.length = 0;
		});
	}

	removeFromLocal({id, index}) {
		if(!confirm('Are you sure?')) {
			return;
		}

		this.filesForUpload.src.splice(index, 1);
		this.filesForUpload.files.splice(index, 1);
	}

	remove({id, index}) {
		if(!confirm('Are you sure?')) {
			return;
		}

		imagesService.remove({domainId: this.domainId, id: id}).then(() => {
			this.filesToDomainMapper.fileIds.splice(index, 1);
			this.$scope.$apply();
		});	
	};

	addEventListenerToFileInput(filesForUpload, $scope, srcTest) {
		var input = document.getElementById('multipartFile');
		input.addEventListener("change", function() {
			var files  = this.files;
			for (var i = 0; i < files.length; i++) {
				filesForUpload.files.push(files[i]);
				filesForUpload.src.push(URL.createObjectURL(files[i]));
				imagesService.updateFormData('files', files[i]);
			}

			$scope.$apply();
		});

	};
}