import React from 'react'
import BesoinAchat from './views/commercial/besoinAchat/BesoinAchat'
import DetailsBesoinAchat from './views/commercial/detailsBesoinAchat/DetailsBesoinAchat'
import BonDeCommande from './views/commercial/bonDeCommande/BonDeCommande'
import DetailsBonDeCommande from './views/commercial/detailsBonDeCommande/DetailsBonDeCommande'
import Articles from './views/commercial/articles/Articles'
import Service from './views/commercial/services/Service'
import Fournisseurs from './views/commercial/fournisseur/Fournisseurs'
import EtatDeStock from './views/commercial/stock/EtatDeStock/EtatDeStock'
import AjoutStock from './views/commercial/stock/AjoutStock/AjoutStock'
import SortieStock from './views/commercial/stock/SortieStock/SortieStock'
import DemandesProformaRecu from './views/commercial/inbox/DemandeProformaRecu/DemandesProformaRecu'
import Accessibilte from './views/commercial/accessibility/Accessibilte'
import { makeRequest } from './Api'

const Dashboard = React.lazy(() => import('./views/dashboard/Dashboard'))
const Colors = React.lazy(() => import('./views/theme/colors/Colors'))
const Typography = React.lazy(() => import('./views/theme/typography/Typography'))

// Base
const Accordion = React.lazy(() => import('./views/base/accordion/Accordion'))
const Breadcrumbs = React.lazy(() => import('./views/base/breadcrumbs/Breadcrumbs'))
const Cards = React.lazy(() => import('./views/base/cards/Cards'))
const Carousels = React.lazy(() => import('./views/base/carousels/Carousels'))
const Collapses = React.lazy(() => import('./views/base/collapses/Collapses'))
const ListGroups = React.lazy(() => import('./views/base/list-groups/ListGroups'))
const Navs = React.lazy(() => import('./views/base/navs/Navs'))
const Paginations = React.lazy(() => import('./views/base/paginations/Paginations'))
const Placeholders = React.lazy(() => import('./views/base/placeholders/Placeholders'))
const Popovers = React.lazy(() => import('./views/base/popovers/Popovers'))
const Progress = React.lazy(() => import('./views/base/progress/Progress'))
const Spinners = React.lazy(() => import('./views/base/spinners/Spinners'))
const Tables = React.lazy(() => import('./views/base/tables/Tables'))
const Tooltips = React.lazy(() => import('./views/base/tooltips/Tooltips'))

// Buttons
const Buttons = React.lazy(() => import('./views/buttons/buttons/Buttons'))
const ButtonGroups = React.lazy(() => import('./views/buttons/button-groups/ButtonGroups'))
const Dropdowns = React.lazy(() => import('./views/buttons/dropdowns/Dropdowns'))

//Forms
const ChecksRadios = React.lazy(() => import('./views/forms/checks-radios/ChecksRadios'))
const FloatingLabels = React.lazy(() => import('./views/forms/floating-labels/FloatingLabels'))
const FormControl = React.lazy(() => import('./views/forms/form-control/FormControl'))
const InputGroup = React.lazy(() => import('./views/forms/input-group/InputGroup'))
const Layout = React.lazy(() => import('./views/forms/layout/Layout'))
const Range = React.lazy(() => import('./views/forms/range/Range'))
const Select = React.lazy(() => import('./views/forms/select/Select'))
const Validation = React.lazy(() => import('./views/forms/validation/Validation'))

const Charts = React.lazy(() => import('./views/charts/Charts'))

// Icons
const CoreUIIcons = React.lazy(() => import('./views/icons/coreui-icons/CoreUIIcons'))
const Flags = React.lazy(() => import('./views/icons/flags/Flags'))
const Brands = React.lazy(() => import('./views/icons/brands/Brands'))

// Notifications
const Alerts = React.lazy(() => import('./views/notifications/alerts/Alerts'))
const Badges = React.lazy(() => import('./views/notifications/badges/Badges'))
const Modals = React.lazy(() => import('./views/notifications/modals/Modals'))
const Toasts = React.lazy(() => import('./views/notifications/toasts/Toasts'))

const Widgets = React.lazy(() => import('./views/widgets/Widgets'))

export const _routes = [
  { path: '/', exact: true, name: 'Home' },
  { path: '/besoinsachat/*', name: 'Besoins Achat', element: BesoinAchat },
  { path: '/detailsbesoinachat/:idBesoinAchat', name: 'Details Besoin Achat', element: DetailsBesoinAchat },
  { path: '/bondecommandes', name: 'Bon de commande', element: BonDeCommande },
  { path: '/detailsbondecommande/:idBonDeCommande', name: 'Details bon de commande', element: DetailsBonDeCommande },
  { path: '/articles', name: 'Articles', element: Articles },
  { path: '/services', name: 'Services', element: Service},
  { path: '/fournisseurs', name: 'Fournisseurs', element: Fournisseurs},
  { path: '/etatstock', name: 'Etat de stock', element: EtatDeStock },
  { path: '/ajoutstock', name: 'Ajout stock', element: AjoutStock },
  { path: '/sortiestock', name: 'Sortie stock', element: SortieStock },
  { path: '/inbox/proformas', name: 'Demandes proforma', element: DemandesProformaRecu },
  { path: '/accessibilite', name: 'Accessibiltes', element: Accessibilte}
]

const getFilteredRoutes = (routes) => {
  let routesString = []
  routes.map((route, index) => {
    routesString = [...routesString, route.path]
  })

  return new Promise((resolve, reject) => {
      makeRequest({
        url: 'accessibilite/filter',
        requestType: 'POST',
        values: routesString,
        successCallback: (data) => {
          resolve(data)
        },
        failureCallback: (error) => {
          reject(error)
        }
      })
  });
}

const processRoutes = async (routes) => {
  let newRoutes;

  const filteredRoutes = await getFilteredRoutes(routes);
  newRoutes = routes.filter((route) => filteredRoutes.includes(route.path));

  return newRoutes || [];
};


const routes = await processRoutes(_routes);
console.log(routes)
export default routes
