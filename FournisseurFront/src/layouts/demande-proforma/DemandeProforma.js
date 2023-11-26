import { Card, CardContent, CardHeader, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography } from '@mui/material'
import DefaultInfoCard from 'examples/Cards/InfoCards/DefaultInfoCard'
import Footer from 'examples/Footer'
import DashboardLayout from 'examples/LayoutContainers/DashboardLayout'
import DashboardNavbar from 'examples/Navbars/DashboardNavbar'
import React, { useState } from 'react'

const DemandeProforma = () => {
    // Demandes de proforma
    const [ demandesProforma, setDemanesProforma ] = useState([])

  return (
    <DashboardLayout>
        <DashboardNavbar />
            <Card style={{ padding: '1rem' }}>
                <CardContent>
                    <Typography gutterBottom variant='h5' component="div">
                        Liste des demandes
                    </Typography>

                    <TableContainer component={Paper}>
                        <Table>
                            <TableHead>
                                <TableRow>
                                    <TableCell align='left'>Fournisseur</TableCell>
                                    <TableCell align='right'>Article</TableCell>
                                    <TableCell align='right'>Quantite</TableCell>
                                    <TableCell align='right'>Unite</TableCell>
                                    <TableCell align='right'>Email</TableCell>
                                    <TableCell align='right'>Status</TableCell>
                                    <TableCell align='right'>Validation</TableCell>
                                </TableRow>
                            </TableHead>

                            <TableBody>
                                
                            </TableBody>
                        </Table>
                    </TableContainer>
                </CardContent>
            </Card>
        <Footer />
    </DashboardLayout>
  )
}

export default DemandeProforma