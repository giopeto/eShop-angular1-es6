const call = ({url, method, payload}) => {
	
	let request = new Request(url, {
		method: method,
		body: payload && JSON.stringify(payload) || null,
		mode: 'cors',
		credentials: 'include',
		headers: new Headers({
			'Content-Type': 'application/json;charset=UTF-8'
		})
	});

	return fetch(request)
		.then(response => handleErrors(response))
		.then(response => handleResponse(response))
		.catch(error => console.log(error));
}

const handleErrors = response => {
	if (!response.ok) {
		throw Error(response.statusText);
	}
	return response;
}

const handleResponse = response => response.json();

export const callGet = url => call({url, method: 'GET'});

export const callPost = (url, payload) => call({url, method: 'POST', payload});

export const callPut = (url, payload) => call({url, method: 'PUT', payload});

export const callDelete = url => call({url, method: 'DELETE'});

export const API_PREFIX = 'api';

export const API_VERSION = 'V1';

export const API_BASE_URL = `http://localhost:9000/${API_PREFIX}/${API_VERSION}`;