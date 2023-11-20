import { CButton, CCard, CCardBody, CCardTitle, CCol, CRow, CTable, CTableBody, CTableDataCell, CTableHead, CTableHeaderCell, CTableRow } from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { getArticles } from '../besoinAchat/components/SelectionArticle'
import CIcon from '@coreui/icons-react'
import { cilPen, cilTrash } from '@coreui/icons'
import { makeRequest } from 'src/Api'

export const allDemandesArticles = () => {
  return new Promise((resolve, reject) => {
    makeRequest({
      url: 'demandeajoutarticle',
      requestType: 'GET',
      successCallback: (data) => {
        resolve(data)
      },
      failureCallback: (error) => [
        reject(error)
      ]
    })
  })
}

export const validerDemande = (idDemandeAjoutArticle) => {
  return new Promise((resolve, reject) => {
    makeRequest({
      url: `demandeajoutarticle/${idDemandeAjoutArticle}/valider`,
      requestType: 'GET',
      successCallback: (data) => {
        resolve(data)
      },
      failureCallback: (error) => [
        reject(error)
      ]
    })
  })
}

const Articles = () => {
  const [ update, setUpdate ] = useState(false)

  // Articles 
  const [articles, setArticles] = useState([])
  useEffect(() => {
    getArticles().then((data) => {
      setArticles(data)
    })
  }, [update])

  // Demandes ajouts
  const [demandesAjoutsArticles, setDemandesAjoutsArticles] = useState([])
  useEffect(() => {
    allDemandesArticles().then((data) => {
      setDemandesAjoutsArticles(data)
    })
  }, [update])

  return (
    <CCard>
      <CCardBody>
        <CCardTitle>
          <h3>Articles</h3>
          <CButton>Ajouter article</CButton>
        </CCardTitle>

        <CRow className='mt-5'>
          <CCol>
            <h6>Demandes ajouts</h6>

            <CTable>
              <CTableHead>
                <CTableRow>
                  <CTableHeaderCell scope='col'>Date de demande</CTableHeaderCell>
                  <CTableHeaderCell scope='col'>Nom article</CTableHeaderCell>
                  <CTableHeaderCell scope='col'></CTableHeaderCell>
                </CTableRow>
              </CTableHead>

              <CTableBody>
                {demandesAjoutsArticles.map((demandes, index) => {
                  return (
                    <CTableRow key={demandes.nomArticle}>
                      <CTableDataCell>
                        <p>{ new Date(demandes.dateLivraison).toLocaleString() }</p>
                      </CTableDataCell>
                      <CTableDataCell>
                        <p>{demandes.nomArticle}</p>
                      </CTableDataCell>
                      <CTableDataCell>
                        <CButton onClick={() => { validerDemande(demandes.idDemandeAjoutArticle).then((data) => { setUpdate(true) }) }} >Valider demande</CButton>
                      </CTableDataCell>
                    </CTableRow>
                  )
                })}
              </CTableBody>
            </CTable>
          </CCol>
        </CRow>

        <CRow className='mt-5'>
          <CCol>
            <h6>Liste articles</h6>

            <CTable>
              <CTableHead>
                <CTableRow>
                  <CTableHeaderCell scope='col'>Nom article</CTableHeaderCell>
                  <CTableHeaderCell scope='col'></CTableHeaderCell>
                </CTableRow>
              </CTableHead>

              <CTableBody>
                {articles.map((article, index) => {
                  return (
                    <CTableRow key={article.nom}>
                      <CTableDataCell>
                        <p className='badge text-bg-info'>{article.nom}</p>
                      </CTableDataCell>
                      <CTableDataCell>
                        <div className='d-flex gap-2 justify-content-end'>
                          <CButton>
                            <CIcon icon={cilPen}></CIcon>
                          </CButton>

                          <CButton color='danger'>
                            <CIcon icon={cilTrash}></CIcon>
                          </CButton>
                        </div>
                      </CTableDataCell>
                    </CTableRow>
                  )
                })}
              </CTableBody>
            </CTable>
          </CCol>
        </CRow>
      </CCardBody>
    </CCard>
  )
}

export default Articles