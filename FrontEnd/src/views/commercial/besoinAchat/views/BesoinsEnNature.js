import { CTable, CTableBody, CTableDataCell, CTableHead, CTableHeaderCell, CTableRow } from '@coreui/react';
import React, { useEffect, useState } from 'react'
import { makeRequest } from 'src/Api';

export const getBesoinEnNature = () => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: '/besoinsachat/nature',
            requestType: 'GET',
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => {
                reject(error)
            }
        })
    });
}

const BesoinsEnNature = () => {
    // Besoins en nature
    const [besoinEnNature, setBesoinEnNature] = useState([])
    useEffect(() => {
        getBesoinEnNature().then((data) => {
            setBesoinEnNature(data)
        })
    }, [])

    return (
        <CTable>
            <CTableHead>
                <CTableRow>
                    <CTableHeaderCell scope='col'>Article</CTableHeaderCell>
                    <CTableHeaderCell scope='col'>QUantite</CTableHeaderCell>
                    <CTableHeaderCell scope='col'>Unite</CTableHeaderCell>
                    <CTableHeaderCell scope='col'>Services</CTableHeaderCell>
                </CTableRow>
            </CTableHead>

            <CTableBody>
                {besoinEnNature.map((besoinEnNature, index) => {
                    return (
                        <CTableRow key={besoinEnNature.article.nom}>
                            <CTableDataCell>
                                <p className='badge text-bg-primary text-wrap'>
                                    {besoinEnNature.article.nom}
                                </p>
                            </CTableDataCell>
                            <CTableDataCell>
                                <p>
                                    {besoinEnNature.nombreTotal}
                                </p>
                            </CTableDataCell>
                            <CTableDataCell>
                                <p>
                                    {besoinEnNature.unite.nom}
                                </p>
                            </CTableDataCell>
                            <CTableDataCell>
                                {besoinEnNature.besoinAchats.map((besoins, index) => {
                                    return (
                                        <p key={index} className='badge text-bg-primary text-wrap'>
                                            { besoins.service.nom }
                                        </p>
                                    )
                                })}
                            </CTableDataCell>
                        </CTableRow>
                    )
                })}
            </CTableBody>
        </CTable>
    )
}

export default BesoinsEnNature