<template>

    <v-data-table
        :headers="headers"
        :items="eventCollector"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'EventCollectorView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
                { text: "type", value: "type" },
                { text: "correlationKey", value: "correlationKey" },
                { text: "payload", value: "payload" },
                { text: "timestamp", value: "timestamp" },
            ],
            eventCollector : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/eventCollectors'))

            temp.data._embedded.eventCollectors.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.eventCollector = temp.data._embedded.eventCollectors;
        },
        methods: {
        }
    }
</script>

