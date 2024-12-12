<template>
    <v-card outlined>
        <v-card-title>
            FinishCook
        </v-card-title>

        <v-card-text>
            <String label="Message" v-model="value.message" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="finishCook"
            >
                FinishCook
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
        name: 'FinishCookCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.message = '';
        },
        watch: {
        },
        methods: {
            finishCook() {
                this.$emit('finishCook', this.value);
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

