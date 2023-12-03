import { CButton, CCard, CCardBody, CCardSubtitle, CCardTitle, CCol, CForm, CFormInput, CFormSelect, CRow, CSpinner, CTable, CTableBody, CTableDataCell, CTableHead, CTableHeaderCell, CTableRow } from '@coreui/react'
import React, { useEffect, useRef, useState } from 'react'
import { useParams } from 'react-router-dom'
import { makeRequest } from 'src/Api'
import { CheckAuth, HasProfil } from 'src/Authenfication'
import DisplayError from '../DisplayError/DisplayError'
import { NombresEnToutesLettres } from 'nombre-en-toutes-lettres'

export const getDetailsBonDeCommande = (idBonDeCommande) => {
	return new Promise((resolve, reject) => {
		makeRequest({
			url: `bondecommandes/${idBonDeCommande}`,
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

export const getModesDePaiement = () => {
	return new Promise((resolve, reject) => {
		makeRequest({
			url: `modesdepaiement`,
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

export const validerBonDeCommande = (idBonDeCommande) => {
	return new Promise((resolve, reject) => {
		makeRequest({
			url: `bondecommandes/${idBonDeCommande}/valider`,
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

const DetailsBonDeCommande = () => {
	const { idBonDeCommande } = useParams()

	// Update
	const [ update, setUpdate ] = useState(false)

	// Utilisateur
    const [ user, setUser ] = useState()

    useEffect(() => {
        CheckAuth().then((user) => {
            setUser(user)
        })
    }, [])

	// Obtenur bon de commande
	const [bonDeCommande, setBonDeCommande] = useState()
	useEffect(() => {
		getDetailsBonDeCommande(idBonDeCommande).then((data) => {
			setBonDeCommande(data)
		})
	}, [update])

	// Modes de paiements
	const [optionsModesDePaiement, setOptionsModesDePaiement] = useState([])
	useEffect(() => {
		getModesDePaiement().then((data) => {
			data.forEach((mode) => {
				setOptionsModesDePaiement(prev => ([...prev, { label: mode.nom, value: mode.idModeDePaiement }]))
			})
		})
	}, [])

	// Validation sous-formes
	const modePaiementRef = useRef()
	const conditionPaiementRef = useRef()
	const dateLivraisonRef = useRef()

	//#region Proprietes submit handlers
	const handleSubmitModeDePaiement = (e) => {
		e.preventDefault()

		new Promise((resolve, reject) => {
			makeRequest({
				url: `/bondecommandes/${idBonDeCommande}/modedepaiment?idModeDePaiement=${modePaiementRef.current.value}`,
				requestType: 'GET',
				successCallback: (response) => {
					resolve(response)
				},
				failureCallback: (error) => {
					reject(error)
				}
			})
		}).then((data) => {
			setUpdate(true)
		})
	}

	const handleSubmitConditionDePaiement = (e) => {
		e.preventDefault()

		new Promise((resolve, reject) => {
			makeRequest({
				url: `/bondecommandes/${idBonDeCommande}/conditiondepaiement?conditionDePaiement=${conditionPaiementRef.current.value}`,
				requestType: 'GET',
				successCallback: (response) => {
					resolve(response)
				},
				failureCallback: (error) => {
					reject(error)
				}
			})
		}).then((data) => {
			setUpdate(true)
		})
	}

	const handleSubmitDateLivraison = (e) => {
		e.preventDefault()

		new Promise((resolve, reject) => {
			makeRequest({
				url: `/bondecommandes/${idBonDeCommande}/datelivraison?dateLivraison=${dateLivraisonRef.current.value}`,
				requestType: 'GET',
				successCallback: (response) => {
					resolve(response)
				},
				failureCallback: (error) => {
					reject(error)
				}
			})
		}).then((data) => {
			setUpdate(true)
		})
	}
	//#endregion

	// Error display ref
	const displayErrorRef = useRef()

	const handleValidationBonDeCommande = (e) => {
		validerBonDeCommande(idBonDeCommande).then((data) => {
			if (data.errorMessage) {
				displayErrorRef.current.showError(data.errorMessage)
			} else {
				setUpdate(true)
			}
		})
	}

	return (
		<>
			<DisplayError ref={displayErrorRef} />

			<CCard className='p-2'>
				<CCardBody>
					<CCardTitle>
						<h3>Bon de commande</h3>
					</CCardTitle>

					<CCardSubtitle className='mb-2 text-medium-emphasis d-flex w-100 justify-content-between'>
						<div className='details-societe'>
							<p className='m-0' >Societe de Madagascar</p>
							<p className='m-0 fw-lighter'>Antananarivo, 101</p>
						</div>

						<div className='details-bon-de-commande d-flex flex-column gap-5'>
							<div className='text-end'>
								<p className='m-0'>Numero commande: <b>Nº {bonDeCommande && bonDeCommande.numeroBonDeCommande}</b></p>
								<p className='fw-lighter m-0' style={{ fontSize: '.8rem' }}>Date: {bonDeCommande && new Date(bonDeCommande.dateCreation).toLocaleString()}</p>
							</div>

							<div className='text-end'>
								<p className='m-0' style={{ fontSize: '.9rem' }}>Vers: <b>{bonDeCommande && bonDeCommande.fournisseur.nom}</b></p>
								<p className='m-0 fw-light ' style={{ fontSize: '.8rem' }}>{bonDeCommande && bonDeCommande.fournisseur.addresse}</p>

								<div className='mt-2'>
									<p className='m-0 fw-light text-wrap' style={{ fontSize: '.8rem' }}>{bonDeCommande && bonDeCommande.fournisseur.email}</p>
									<p className='m-0 fw-light text-wrap' style={{ fontSize: '.8rem' }}>Contact: {bonDeCommande && bonDeCommande.fournisseur.contact}</p>
								</div>

								<div className='mt-2'>
									<p className='m-0 fw-bold text-wrap' style={{ fontSize: '.8rem' }}>{bonDeCommande && bonDeCommande.fournisseur.nomResponsable}</p>
									<p className='m-0 fw-light text-wrap' style={{ fontSize: '.8rem' }}>Contact: {bonDeCommande && bonDeCommande.fournisseur.contactResponsable}</p>
								</div>
							</div>
						</div>
					</CCardSubtitle>

					<CRow className='p-3'>
						<CCol>
							<CTable striped>
								<CTableHead>
									<CTableRow>
										<CTableHeaderCell scope='col'>Vendeur</CTableHeaderCell>
										<CTableHeaderCell scope='col'>Paiement</CTableHeaderCell>
										<CTableHeaderCell scope='col'>Conditions</CTableHeaderCell>
										<CTableHeaderCell scope='col'>Echeance</CTableHeaderCell>
									</CTableRow>
								</CTableHead>

								<CTableBody>
									<CTableRow>
										<CTableDataCell>
											<p className='text-medium-emphasis' style={{ fontSize: '.8rem' }} >
												{bonDeCommande && bonDeCommande.fournisseur.nom}
											</p>
										</CTableDataCell>

										<CTableDataCell>
											<p className='text-medium-emphasis' style={{ fontSize: '.8rem' }} >
												{bonDeCommande && bonDeCommande.idModeDePaiement == 0 ? (
													<CForm onSubmit={handleSubmitModeDePaiement} className='d-flex gap-1 flex-column'>
														<CFormSelect floatingLabel="Modes de paiement" ref={modePaiementRef} options={optionsModesDePaiement} />
														<CButton type='submit'>Ajouter</CButton>
													</CForm>
												) : bonDeCommande && bonDeCommande.modeDePaiement.nom}
											</p>
										</CTableDataCell>

										<CTableDataCell>
											<p className='text-medium-emphasis' style={{ fontSize: '.8rem' }} >
												{bonDeCommande && bonDeCommande.conditionDePaiement == null ? (
													<CForm onSubmit={handleSubmitConditionDePaiement} className='d-flex gap-1 flex-column'>
														<CFormInput floatingLabel="Condition de paiement" ref={conditionPaiementRef} type='text' />
														<CButton type='submit'>Ajouter</CButton>
													</CForm>
												) : bonDeCommande && bonDeCommande.conditionDePaiement}
											</p>
										</CTableDataCell>

										<CTableDataCell>
											<p className='text-medium-emphasis' style={{ fontSize: '.8rem' }} >
												{bonDeCommande && bonDeCommande.dateLivraison == null ? (
													<CForm onSubmit={handleSubmitDateLivraison} className='d-flex gap-1 flex-column'>
														<CFormInput floatingLabel="Date livraison" ref={dateLivraisonRef} type='datetime-local' />
														<CButton type='submit'>Ajouter</CButton>
													</CForm>
												) : bonDeCommande && new Date(bonDeCommande.dateLivraison).toLocaleString() }
											</p>
										</CTableDataCell>
									</CTableRow>
								</CTableBody>
							</CTable>
						</CCol>
					</CRow>

					<CRow className='mt-2'>
						<CCol>

							<h5>
								Details
							</h5>

							<CTable bordered striped>
								<CTableHead>
									<CTableRow>
										<CTableHeaderCell scope='col'>Designation</CTableHeaderCell>
										<CTableHeaderCell scope='col'>Quantite</CTableHeaderCell>
										<CTableHeaderCell scope='col'>Prix Unitaire (HT)</CTableHeaderCell>
										<CTableHeaderCell scope='col'>Total Prix (HT)</CTableHeaderCell>
										<CTableHeaderCell scope='col'>TVA</CTableHeaderCell>
										<CTableHeaderCell scope='col'>Total Prix (TTC)</CTableHeaderCell>
									</CTableRow>
								</CTableHead>

								<CTableBody>
									{bonDeCommande && bonDeCommande.articleBonDeCommandes.map((articleBonDeCommande, index) => {
										return (
											<CTableRow key={index}>
												<CTableDataCell>
													<p style={{ fontSize: '.8rem' }} className='fw-bold'>
														{articleBonDeCommande.article.nom}
													</p>
												</CTableDataCell>

												<CTableDataCell>
													<p style={{ fontSize: '.8rem' }}>
														{articleBonDeCommande.quantite}
													</p>
												</CTableDataCell>

												<CTableDataCell>
													<p style={{ fontSize: '.8rem' }}>
														{articleBonDeCommande.prixUnitaireHT} Ar
													</p>
												</CTableDataCell>

												<CTableDataCell>
													<p style={{ fontSize: '.8rem' }}>
														{articleBonDeCommande.totalPrixHorsTaxe} Ar
													</p>
												</CTableDataCell>

												<CTableDataCell>
													<p style={{ fontSize: '.8rem' }}>
														{articleBonDeCommande.tva} Ar
													</p>
												</CTableDataCell>

												<CTableDataCell>
													<p style={{ fontSize: '.8rem' }}>
														{articleBonDeCommande.totalPrixTTC} Ar
													</p>
												</CTableDataCell>
											</CTableRow>
										)
									})}
								</CTableBody>
							</CTable>
						</CCol>
					</CRow>

					<CRow className='mt-2 text-end'>
						<CCol>
							<p className='m-0 fw-light ' style={{ fontSize: '.9rem' }}>Total HT: <b>{bonDeCommande && bonDeCommande.totalPrixHT}</b> Ar</p>
							<p className='m-0 fw-light ' style={{ fontSize: '.9rem' }}>Total TVA: <b>{bonDeCommande && bonDeCommande.totalPrixTVA}</b> Ar</p>
							{ bonDeCommande && (bonDeCommande.isValidationComplete && <p className='m-0 ' style={{ fontSize: '.9rem' }}>Arreter le present bon de commande a la somme de <b>{bonDeCommande && NombresEnToutesLettres(bonDeCommande.totalPrixTTC)}</b> Ar</p>) }
						</CCol>
					</CRow>

					<CRow className='mt-2'>
						{ bonDeCommande && (HasProfil(user, bonDeCommande.canBeValidated) && <CButton onClick={handleValidationBonDeCommande}>Valider</CButton>) }
					</CRow>
				</CCardBody>
			</CCard>
		</>
	)
}

export default DetailsBonDeCommande