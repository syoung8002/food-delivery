<template>
    <v-card outlined>
        <v-card-title>
            StartCook
        </v-card-title>

        <v-card-text>
            <OrderMgmtStatus offline label="Status" v-model="value.status" :editMode="editMode" @change="change"/>
            <String label="Message" v-model="value.message" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="startCook"
            >
                StartCook
            </v-btn>
            
            <v-btn
                    color="primary"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'StartCookCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.status = {};
            this.value.message = '';
        },
        watch: {
        },
        methods: {
            startCook() {
                this.$emit('startCook', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

