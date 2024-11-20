<template>

    <v-data-table
        :headers="headers"
        :items="allocationStatus"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'AllocationStatusView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            allocationStatus : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/allocationStatuses'))

            temp.data._embedded.allocationStatuses.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.allocationStatus = temp.data._embedded.allocationStatuses;
        },
        methods: {
        }
    }
</script>

