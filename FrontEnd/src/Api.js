import axios from 'axios'

const API = axios.create({
  baseURL: 'http://localhost:8080',
  responseType: 'json',
  withCredentials: true
})

const getRequestConfiguration = (authorization) => {
  const headers = {
    'Content-Type': 'application/json',
  }
  if (authorization) headers.Authorization = `Bearer ${authorization}`
  return { headers }
}

const getRequestFormConfiguration = (authorization) => {
    const headers = {
      'Content-Type': 'multipart/form-data',
    }
    if (authorization) headers.Authorization = `Bearer ${authorization}`
    return { headers }
  }

export const makeRequest = ({
  url,
  values,
  successCallback,
  failureCallback,
  requestType = 'POST',
  authorization = null,
  isFormData = false
}) => {
  const requestConfiguration = isFormData ? getRequestFormConfiguration(authorization) : getRequestConfiguration(authorization)
  let promise

  switch (requestType) {
    case 'GET':
      promise = API.get(url, requestConfiguration)
      break
    case 'POST':
      promise = API.post(url, values, requestConfiguration)
      break
    case 'DELETE':
      promise = API.delete(url, requestConfiguration)
      break
    case 'PUT':
      promise = API.put(url, values, requestConfiguration)
      break
    default:
      return
  }

  promise
    .then((response) => {
      const { data } = response
      successCallback(data)
    })
    .catch((error) => {
      if (error.response) {
        failureCallback(error.response.data)
      }
    })
}
