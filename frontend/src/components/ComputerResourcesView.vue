<template>

    <v-data-table
        :headers="headers"
        :items="computerResources"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'ComputerResourcesView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            computerResources : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/computerResources'))

            temp.data._embedded.computerResources.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.computerResources = temp.data._embedded.computerResources;
        },
        methods: {
        }
    }
</script>

