import { cilArrowLeft, cilArrowRight, cilArrowThickRight } from '@coreui/icons'
import CIcon from '@coreui/icons-react'
import { CCard, CCardBody, CCardTitle, CCol, CRow, CTable, CTableHeaderCell, CTableRow, CTableHead, CTableBody, CTableDataCell, CButton, CNavLink } from '@coreui/react'
import React, { useEffect, useState } from 'react'
import { NavLink } from 'react-router-dom'
import { makeRequest } from 'src/Api'

export const getBonDeCommandes = () => {
	return new Promise((resolve, reject) => {
		makeRequest({
			url: 'bondecommandes',
			requestType: 'GET',
			successCallback: (response) => {
				resolve(response)
			},
			failureCallback: (error) => {
				reject(error)
			}
		})
	})
}

const BonDeCommande = () => {
	// Obtenir les bons de commandes
	const [ bonDeCommandes, setBonDeCommandes ] = useState([])
	useEffect(() => {
		getBonDeCommandes().then((data) => {
			setBonDeCommandes(data)
		}) 
	}, [])

	return (
		<CCard className='p-2'>
			<CCardBody>
				<CCardTitle>
					<h6>Bon de commandes</h6>
				</CCardTitle>

				<CRow>
					<CCol>
						<CTable>
							<CTableHead>
								<CTableRow>
									<CTableHeaderCell scope='col'>Numero</CTableHeaderCell>
									<CTableHeaderCell scope='col'>Titre</CTableHeaderCell>
									<CTableHeaderCell scope='col'>Fournisseur</CTableHeaderCell>
									<CTableHeaderCell scope='col'>HT</CTableHeaderCell>
									<CTableHeaderCell scope='col'>TVA</CTableHeaderCell>
									<CTableHeaderCell scope='col'>TTC</CTableHeaderCell>
									<CTableHeaderCell scope='col'>Articles</CTableHeaderCell>
									<CTableHeaderCell scope='col'>Status</CTableHeaderCell>
									<CTableHeaderCell scope='col'></CTableHeaderCell>
								</CTableRow>
							</CTableHead>

							<CTableBody>
								{ bonDeCommandes.map((bonDeCommande, index) => {
									return (
										<CTableRow key={index}>
											<CTableDataCell>
												<p style={{ fontSize: '.8rem' }} className='fw-bold'>
												Nº { bonDeCommande.numeroBonDeCommande }
												</p>
											</CTableDataCell>

											<CTableDataCell>
												<p style={{ fontSize: '.8rem' }}>
												{ bonDeCommande.titre ? bonDeCommande.titre : "Aucun titre" }
												</p>
											</CTableDataCell>

											<CTableDataCell>
												<p style={{ fontSize: '.8rem' }} className='fw-bold text-medium-emphasis'>
													{ bonDeCommande.fournisseur.nom }
												</p>
											</CTableDataCell>

											<CTableDataCell>
												<p style={{ fontSize: '.8rem' }}>
												{ bonDeCommande.totalPrixHT } Ar
												</p>
											</CTableDataCell>

											<CTableDataCell>
												<p style={{ fontSize: '.8rem' }}>
												{ bonDeCommande.totalPrixTVA } Ar
												</p>
											</CTableDataCell>

											<CTableDataCell>
												<p style={{ fontSize: '.8rem' }}>
												{ bonDeCommande.totalPrixTTC } Ar
												</p>
											</CTableDataCell>

											<CTableDataCell>
												<div className='d-flex gap-2 flex-column'>
													{ bonDeCommande.articleBonDeCommandes.map(( articleBonDeCommande, index ) => {
														return index < 3 ? (
															<p className='badge text-bg-primary text-wrap' key={ articleBonDeCommande.article.nom }>{ articleBonDeCommande.article.nom }</p>
														) : null
													}) }
												</div>
											</CTableDataCell>

											<CTableDataCell>
												<p className='badge text-bg-info text-wrap'>
													{ bonDeCommande.statusString }
												</p>
											</CTableDataCell>

											<CTableDataCell>
												<CNavLink to={`/detailsbondecommande/${ bonDeCommande.idBonDeCommande }`} component={NavLink}>
													<CButton style={{ fontSize: '.8rem' }}>Plus <CIcon icon={cilArrowRight}></CIcon></CButton>
												</CNavLink>
											</CTableDataCell>
										</CTableRow>
									)
								}) }
							</CTableBody>
						</CTable>
					</CCol>
				</CRow>
			</CCardBody>
		</CCard>
	)
}

export default BonDeCommande