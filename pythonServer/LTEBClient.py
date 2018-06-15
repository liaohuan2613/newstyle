# -*-coding:utf-8-*-

import requests

uid = None


def login():
    '''登录接口:/auth/login'''
    s = requests.Session()
    r = s.post(
        url='http://127.0.0.1:8081',
        data={'username': 'system', 'password': '123456'})
    return s


def selectable(uid):
    r = login().get(
        url='http://127.0.0.1:8081')
    print r.text
    if uid is None:
        uid = r.text
    print uid


def close():
    s = requests.session()
    s.post(url='http://127.0.0.1:8081',
           data={'username': 'system', 'remove': 'system'})


selectable(uid)
