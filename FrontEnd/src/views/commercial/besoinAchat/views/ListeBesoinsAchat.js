import { CCol, CNav, CNavItem, CNavLink, CRow } from '@coreui/react'
import React from 'react'
import { NavLink, Route, Routes } from 'react-router-dom'
import ListeOpenBesoinsAchat from './ListeOpenBesoinsAchat'
import ListeClosedBesoinsAchat from './ListeClosedBesoinsAchat'
import BesoinsEnNature from './BesoinsEnNature'

const ListeBesoinsAchat = (props) => {
    return (
        <CRow>
            <CCol>
                <CNav variant="tabs">
                    <CNavItem>
                        <CNavLink to='open' component={NavLink}>
                            En cours
                        </CNavLink>
                    </CNavItem>
                    <CNavItem>
                        <CNavLink to='closed' component={NavLink}>Fermees</CNavLink>
                    </CNavItem>
                    <CNavItem>
                        <CNavLink to='nature' component={NavLink}>En Nature</CNavLink>
                    </CNavItem>
                </CNav>

                {/* Navigations routes */}
                <Routes>
                    <Route path='/open' element={<ListeOpenBesoinsAchat />} />
                    <Route path='/closed' element={ <ListeClosedBesoinsAchat /> } />
                    <Route path='/nature' element={ <BesoinsEnNature /> } />
                </Routes>
            </CCol>
        </CRow>
    )
}

export default ListeBesoinsAchat