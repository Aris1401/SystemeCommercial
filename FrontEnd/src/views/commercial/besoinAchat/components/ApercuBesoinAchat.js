import { cilArrowRight, cilCaretRight, cilChevronCircleRightAlt } from '@coreui/icons'
import CIcon from '@coreui/icons-react'
import { CBadge, CButton, CLink, CNavLink, CTable, CTableBody, CTableDataCell, CTableRow } from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { useStore } from 'react-redux'
import { Link, NavLink } from 'react-router-dom'
import { makeRequest } from 'src/Api'

export const getDetailsService = (idService) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: `services/${idService}`,
            requestType: 'GET',
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => {
                reject(error)
            }
        })
    })
}

export const getArticlesBesoinAchat = (idBesoinAchat) => {
    return new Promise((resolve, reject) => {
        makeRequest(
            {
                url: `besoinsachat/${idBesoinAchat}/articles`,
                requestType: 'GET',
                successCallback: (response) => { resolve(response); },
                failureCallback: (error) => { reject(error) }
            }
        )
    })
}

const ApercuBesoinAchat = (props) => {
    const { besoinAchat } = props

    // Obtenir informations sur le service
    const [service, setService] = useState()
    useEffect(() => {
        getDetailsService(besoinAchat.idService).then((data) => {
            setService(data);
        })
    }, [])

    // Articles besoins achat
    const [articlesBesoinAchat, setArticlesBesoinAchat] = useState([])
    useEffect(() => {
        getArticlesBesoinAchat(besoinAchat.idBesoinAchat).then((data) => {
            setArticlesBesoinAchat(data);
        })
    }, [])

    return (
        <CTableRow>
            <CTableDataCell className='p-2'>
                <h6 className='mb-0'>
                    {service && service.nom}
                </h6>
                <p className='text-secondary m-0' style={{ fontSize: '.75rem' }}>
                    {new Date(besoinAchat.dateBesoin).toLocaleDateString()}
                    <CIcon icon={cilArrowRight}></CIcon>
                    {new Date(besoinAchat.dateCloture).toLocaleDateString()}
                </p>
                <p className='mt-3' style={{ fontSize: '.8rem' }}>
                    {besoinAchat.description}
                </p>
            </CTableDataCell>

            <CTableDataCell className='p-2'>
                <div className='d-flex g-2'>
                    {articlesBesoinAchat.map((articlesQuantite, index) => {
                        return <CBadge color='info' className='user-select-none' key={index}>{articlesQuantite.article.nom}</CBadge>;
                    })}
                </div>
            </CTableDataCell>

            <CTableDataCell style={{ verticalAlign: 'middle' }}>
                <CNavLink to={ `/detailsbesoinachat/${besoinAchat.idBesoinAchat}` } component={NavLink}>
                    <CButton>
                        <CIcon icon={cilChevronCircleRightAlt}></CIcon>
                    </CButton>
                </CNavLink>
            </CTableDataCell>
        </CTableRow>
    )
}

export default ApercuBesoinAchat