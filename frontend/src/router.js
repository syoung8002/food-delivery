
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import OrderOrderManager from "./components/listers/OrderOrderCards"
import OrderOrderDetail from "./components/listers/OrderOrderDetail"

import ProcessOrderOrderMgmtManager from "./components/listers/ProcessOrderOrderMgmtCards"
import ProcessOrderOrderMgmtDetail from "./components/listers/ProcessOrderOrderMgmtDetail"

import RiderDeliveryManager from "./components/listers/RiderDeliveryCards"
import RiderDeliveryDetail from "./components/listers/RiderDeliveryDetail"


import OrderHistoryView from "./components/OrderHistoryView"
import OrderHistoryViewDetail from "./components/OrderHistoryViewDetail"

import EventCollectorView from "./components/EventCollectorView"
import EventCollectorViewDetail from "./components/EventCollectorViewDetail"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/orders/orders',
                name: 'OrderOrderManager',
                component: OrderOrderManager
            },
            {
                path: '/orders/orders/:id',
                name: 'OrderOrderDetail',
                component: OrderOrderDetail
            },

            {
                path: '/processOrders/orderMgmts',
                name: 'ProcessOrderOrderMgmtManager',
                component: ProcessOrderOrderMgmtManager
            },
            {
                path: '/processOrders/orderMgmts/:id',
                name: 'ProcessOrderOrderMgmtDetail',
                component: ProcessOrderOrderMgmtDetail
            },

            {
                path: '/riders/deliveries',
                name: 'RiderDeliveryManager',
                component: RiderDeliveryManager
            },
            {
                path: '/riders/deliveries/:id',
                name: 'RiderDeliveryDetail',
                component: RiderDeliveryDetail
            },


            {
                path: '/dashboards/orderHistories',
                name: 'OrderHistoryView',
                component: OrderHistoryView
            },
            {
                path: '/dashboards/orderHistories/:id',
                name: 'OrderHistoryViewDetail',
                component: OrderHistoryViewDetail
            },

            {
                path: '/eventcollections/eventCollectors',
                name: 'EventCollectorView',
                component: EventCollectorView
            },
            {
                path: '/eventcollections/eventCollectors/:id',
                name: 'EventCollectorViewDetail',
                component: EventCollectorViewDetail
            },


    ]
})
