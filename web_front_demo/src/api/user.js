import request from '@/utils/request'

export function login(username, password) {
  // 调用utils中的request.js
  return request({
    url: '/user-config/login',
    method: 'post',
    params: { username, password }
    // data
  })
}

export function getInfo(token) {
  return request({
    url: '/user-config/listAll',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/user-config/logout',
    method: 'post'
  })
}
