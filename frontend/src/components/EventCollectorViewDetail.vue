<template>

    <v-card outlined>
        <v-card-title>
            EventCollector # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <String label="Type" v-model="item.type" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="CorrelationKey" v-model="item.correlationKey" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Payload" v-model="item.payload" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="Timestamp" v-model="item.timestamp" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'EventCollectorViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/eventCollectors/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

