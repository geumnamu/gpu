
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import RequestAllocationManager from "./components/listers/RequestAllocationCards"
import RequestAllocationDetail from "./components/listers/RequestAllocationDetail"

import AllowanceAllowanceCheckManager from "./components/listers/AllowanceAllowanceCheckCards"
import AllowanceAllowanceCheckDetail from "./components/listers/AllowanceAllowanceCheckDetail"


import AllocationmanagementAllocationManagementManager from "./components/listers/AllocationmanagementAllocationManagementCards"
import AllocationmanagementAllocationManagementDetail from "./components/listers/AllocationmanagementAllocationManagementDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/requests/allocations',
                name: 'RequestAllocationManager',
                component: RequestAllocationManager
            },
            {
                path: '/requests/allocations/:id',
                name: 'RequestAllocationDetail',
                component: RequestAllocationDetail
            },

            {
                path: '/allowances/allowanceChecks',
                name: 'AllowanceAllowanceCheckManager',
                component: AllowanceAllowanceCheckManager
            },
            {
                path: '/allowances/allowanceChecks/:id',
                name: 'AllowanceAllowanceCheckDetail',
                component: AllowanceAllowanceCheckDetail
            },


            {
                path: '/allocationmanagements/allocationManagements',
                name: 'AllocationmanagementAllocationManagementManager',
                component: AllocationmanagementAllocationManagementManager
            },
            {
                path: '/allocationmanagements/allocationManagements/:id',
                name: 'AllocationmanagementAllocationManagementDetail',
                component: AllocationmanagementAllocationManagementDetail
            },



    ]
})
