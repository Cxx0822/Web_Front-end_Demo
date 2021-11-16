import request from '@/utils/request'

export function getList() {
  return request({
    url: '/user-info/listAll',
    method: 'get'
  })
}

export function register(userInfo) {
  return request({
    url: '/user-info/register',
    method: 'post',
    data: userInfo
  })
}

export function deleteByName(name) {
  return request({
    url: '/user-info/delete-userInfo-byName/' + name,
    method: 'delete'
  })
}

export function updateByName(name, userInfo) {
  return request({
    url: '/user-info/update-userInfo-byName/' + name,
    method: 'post',
    data: userInfo
  })
}

