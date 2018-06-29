import * as Api from '../../Api';

class ImagesClass {

	constructor() {
		this.IMAGES_URL = `${Api.API_BASE_URL}/files`;
		this.formData = new FormData();
	}

	getImagesUrl() {
		return this.IMAGES_URL;
	}

	save(domainId) {
		if (this.formData.get('files')) {
			this.updateFormData('domainId', domainId);
			return Api.callMulty(this.IMAGES_URL, this.formData)
				.then(() => this.formData = new FormData());
		} else {
			return Promise.resolve();
		}
	}

	updateFormData(name, payload) {
		this.formData.append(name, payload);
	}

	getByDomainId(domainId) {
		return Api.callGet(`${this.IMAGES_URL}/${domainId}`);
	}

	remove({domainId, id}) {
		return Api.callDelete(`${this.IMAGES_URL}/${id}/domainId/${domainId}`);
	}
}

const ImagesService = new ImagesClass();
export default ImagesService;