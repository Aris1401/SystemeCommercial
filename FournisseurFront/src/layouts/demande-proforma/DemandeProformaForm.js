import { Box, Button, Card, CardContent, FormControl, FormGroup, Input, InputAdornment, InputLabel, MenuItem, Select, TextField, Typography } from '@mui/material'
import { makeRequest } from 'Api'
import Footer from 'examples/Footer'
import DashboardLayout from 'examples/LayoutContainers/DashboardLayout'
import DashboardNavbar from 'examples/Navbars/DashboardNavbar'
import Dashboard from 'layouts/dashboard'
import React, { useEffect, useState } from 'react'

export const getArticles = () => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: 'articles',
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

export const getUnites = () => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: 'unites',
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

export const getFournisseurs = () => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: '/fournisseurs/own',
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

export const sendDemande = (data) => {
    return new Promise((resolve, reject) => {
        makeRequest({
            url: `/demandeproforma/fournisseur`,
            requestType: 'POST',
            values: data,
            successCallback: (data) => {
                resolve(data)
            },
            failureCallback: (error) => {
                reject(error)
            }
        })
    });
}

const DemandeProformaForm = () => {
    // Input values
    const [fournisseur, setFournisseur] = useState('')
    const [article, setArticle] = useState('')
    const [unite, setUnite] = useState('')
    const [ quantite, setQuantite ] = useState('')

    // Selects
    const [ articles, setArticles ] = useState([])
    const [ unites, setUnites ] = useState([])
    const [ fournisseurs, setFournisseurs ] = useState([])
    useEffect(() => {
        getArticles().then((data) => {
            setArticles(data)
        })

        getUnites().then((data) => {
            setUnites(data)
        })

        getFournisseurs().then((data) => {
            setFournisseurs(data.data)
        })
    }, [])    

    const handleFaireDemandeButton = (e) => {
        let demande = {
            idArticle: article,
            idFournisseur: fournisseur,
            idUnite: unite,
            quantite: quantite
        }

        sendDemande(demande).then((data) => {
            console.log(data)
        })
    }

    return (
        <DashboardLayout>
            <DashboardNavbar />

            <Card style={{ padding: '1rem' }}>
                <CardContent>
                    <Typography gutterBottom variant='h5' component={'div'}>
                        Faire une demande
                    </Typography>

                    <Box>
                        <FormControl fullWidth>
                            <InputLabel id='fournisseur'>Fournisseur</InputLabel>
                            <Select
                                style={{ height: '3rem' }}
                                labelId='fournisseur'
                                id='fournisseur-select'
                                label="Fournisseur"
                                value={fournisseur}
                                onChange={(e) => {
                                    setFournisseur(e.target.value)
                                }}
                            >
                                { fournisseurs.map((fournisseur, index) => {
                                    return <MenuItem key={fournisseur.nom} value={fournisseur.idFournisseur}>{ fournisseur.nom }</MenuItem>
                                }) }
                            </Select>
                        </FormControl>
                    </Box>

                    <Box marginTop={2}>
                        <FormControl fullWidth>
                            <InputLabel id='article'>Article</InputLabel>
                            <Select
                                style={{ height: '3rem' }}
                                labelId='article'
                                id='article-select'
                                label="Article"
                                value={article}
                                onChange={(e) => {
                                    setArticle(e.target.value)
                                }}
                            >
                                { articles.map((article, index) => {
                                    return (<MenuItem key={article.nom} value={article.idArticle}>{ article.nom }</MenuItem>)
                                }) }
                            </Select>
                        </FormControl>
                    </Box>

                    <Box marginTop={2} display={'flex'} gap={3} alignItems={'center'}>
                        <FormControl style={{ width: '50%', height: '3rem' }}>
                            <TextField value={quantite} onChange={(e) => { setQuantite(e.target.value) }} label="Quantite" />
                        </FormControl>

                        <FormControl style={{ width: '50%' }}>
                            <InputLabel id='unite'>Unite</InputLabel>
                            <Select
                                style={{ height: '3rem' }}
                                labelId='unite'
                                id='unite-select'
                                label="Unite"
                                value={unite}
                                onChange={(e) => {
                                    setUnite(e.target.value)
                                }}
                            >
                                { unites.map((unite, index) => {
                                    return <MenuItem key={unite.nom} value={unite.idUnite}>{ unite.nom }</MenuItem>
                                }) }
                                
                            </Select>
                        </FormControl>
                    </Box>

                    <Box marginTop={2}>
                        <Button onClick={handleFaireDemandeButton} fullWidth variant='contained' color='primary'>Faire une demande</Button>
                    </Box>
                </CardContent>
            </Card>

            <Footer />
        </DashboardLayout>
    )
}

export default DemandeProformaForm