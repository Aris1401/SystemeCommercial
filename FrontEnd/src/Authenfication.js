import React from 'react'
import { makeRequest } from './Api'

export const doLogin = (loginInformations) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: '/login',
            requestType: 'POST',
            values: loginInformations,
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => [
                reject(error)
            ]
        })
    })
}

export var HasProfil = (user, profils) => {
    let value = false;
    if (user) {
        console.log(profils)
        console.log(user)
        user.profils.forEach((profil) => {
            profils.forEach((parameterProfil) => {
                if (profil.idProfil === parameterProfil) {
                    value = true;
                }
            })
        })
    } else return false;

    return value;
}

export const CheckAuth = () => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: '/checkAuth',
            requestType: 'POST',
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => [
                reject(error)
            ]
        })
    })
}