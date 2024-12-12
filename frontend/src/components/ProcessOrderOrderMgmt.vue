<template>
    <v-card style="width:450px; height:100%;" outlined>
        <template slot="progress">
            <v-progress-linear
                    color="primary-darker-1"
                    height="10"
                    indeterminate
            ></v-progress-linear>
        </template>

        <v-card-title v-if="value._links">
            OrderMgmt # {{decode(value._links.self.href.split("/")[value._links.self.href.split("/").length - 1])}}
        </v-card-title >
        <v-card-title v-else>
            OrderMgmt
        </v-card-title >        

        <v-card-text style="background-color: white;">
            <String label="OrderId" v-model="value.orderId" :editMode="editMode" :inputUI="''"/>
            <String label="UserId" v-model="value.userId" :editMode="editMode" :inputUI="''"/>
            <String label="StoreId" v-model="value.storeId" :editMode="editMode" :inputUI="''"/>
            <Number label="TotalAmount" v-model="value.totalAmount" :editMode="editMode" :inputUI="''"/>
            <String label="Comment" v-model="value.comment" :editMode="editMode" :inputUI="''"/>
            <OrderItemManager offline label="OrderItems" v-model="value.orderItems" :editMode="editMode" @change="change"/>
            <Status offline label="Status" v-model="value.status" :editMode="editMode" @change="change"/>
            <Address offline label="Address" v-model="value.address" :editMode="editMode" @change="change"/>
            <Number label="Coupon" v-model="value.coupon" :editMode="editMode" :inputUI="''"/>
        </v-card-text>

        <v-card-actions style="background-color: white;">
            <v-spacer></v-spacer>
            <v-btn
                color="primary"
                text
                @click="edit"
                v-if="!editMode"
            >
                수정
            </v-btn>
            <div v-else>
                <v-btn
                    color="primary"
                    text
                    @click="save"
                >
                저장
                </v-btn>
                <v-btn
                    color="primary"
                    text
                    @click="remove"
                    v-if="!editMode"
                >
                    삭제
                </v-btn>
                <v-btn
                    color="primary"
                    text
                    @click="editMode = false"
                    v-if="editMode && !isNew"
                >
                    취소
                </v-btn>
            </div>
        </v-card-actions>
        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                v-if="!editMode"
                color="primary"
                text
                @click="openProcessOrder"
            >
                ProcessOrder
            </v-btn>
            <v-dialog v-model="processOrderDiagram" width="500">
                <ProcessOrderCommand
                    @closeDialog="closeProcessOrder"
                    @processOrder="processOrder"
                ></ProcessOrderCommand>
            </v-dialog>
            <v-btn
                v-if="!editMode"
                color="primary"
                text
                @click="openStartCook"
            >
                StartCook
            </v-btn>
            <v-dialog v-model="startCookDiagram" width="500">
                <StartCookCommand
                    @closeDialog="closeStartCook"
                    @startCook="startCook"
                ></StartCookCommand>
            </v-dialog>
            <v-btn
                v-if="!editMode"
                color="primary"
                text
                @click="openFinishCook"
            >
                FinishCook
            </v-btn>
            <v-dialog v-model="finishCookDiagram" width="500">
                <FinishCookCommand
                    @closeDialog="closeFinishCook"
                    @finishCook="finishCook"
                ></FinishCookCommand>
            </v-dialog>
        </v-card-actions>

        <v-snackbar
            v-model="snackbar.status"
            :top="true"
            :timeout="snackbar.timeout"
            color="error"
        >
            {{ snackbar.text }}
            <v-btn dark text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
    </v-card>

</template>

<script>
    const axios = require('axios').default;

    import Address from './vo/Address.vue';

    export default {
        name: 'ProcessOrderOrderMgmt',
        components:{
            Address,
        },
        props: {
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            offline: Boolean,
        },
        data: () => ({
            snackbar: {
                status: false,
                timeout: 5000,
                text: '',
            },
            processOrderDiagram: false,
            startCookDiagram: false,
            finishCookDiagram: false,
        }),
	async created() {
        },
        methods: {
            decode(value) {
                return decodeURIComponent(value);
            },
            selectFile(){
                if(this.editMode == false) {
                    return false;
                }
                var me = this
                var input = document.createElement("input");
                input.type = "file";
                input.accept = "image/*";
                input.id = "uploadInput";
                
                input.click();
                input.onchange = function (event) {
                    var file = event.target.files[0]
                    var reader = new FileReader();

                    reader.onload = function () {
                        var result = reader.result;
                        me.imageUrl = result;
                        me.value.photo = result;
                        
                    };
                    reader.readAsDataURL( file );
                };
            },
            edit() {
                this.editMode = true;
            },
            async save(){
                try {
                    var temp = null;

                    if(!this.offline) {
                        if(this.isNew) {
                            temp = await axios.post(axios.fixUrl('/orderMgmts'), this.value)
                        } else {
                            temp = await axios.put(axios.fixUrl(this.value._links.self.href), this.value)
                        }
                    }

                    if(this.value!=null) {
                        for(var k in temp.data) this.value[k]=temp.data[k];
                    } else {
                        this.value = temp.data;
                    }

                    this.editMode = false;
                    this.$emit('input', this.value);

                    if (this.isNew) {
                        this.$emit('add', this.value);
                    } else {
                        this.$emit('edit', this.value);
                    }

                    location.reload()

                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
                
            },
            async remove(){
                try {
                    if (!this.offline) {
                        await axios.delete(axios.fixUrl(this.value._links.self.href))
                    }

                    this.editMode = false;
                    this.isDeleted = true;

                    this.$emit('input', this.value);
                    this.$emit('delete', this.value);

                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            change(){
                this.$emit('input', this.value);
            },
            async processOrder(params) {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links['processorder'].href), params)
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                    this.closeProcessOrder();
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            openProcessOrder() {
                this.processOrderDiagram = true;
            },
            closeProcessOrder() {
                this.processOrderDiagram = false;
            },
            async startCook(params) {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links['startcook'].href), params)
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                    this.closeStartCook();
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            openStartCook() {
                this.startCookDiagram = true;
            },
            closeStartCook() {
                this.startCookDiagram = false;
            },
            async finishCook(params) {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links['finishcook'].href), params)
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                    this.closeFinishCook();
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            openFinishCook() {
                this.finishCookDiagram = true;
            },
            closeFinishCook() {
                this.finishCookDiagram = false;
            },
        },
    }
</script>

