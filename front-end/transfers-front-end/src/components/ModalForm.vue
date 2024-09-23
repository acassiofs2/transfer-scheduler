<template>
    <div v-if="isOpen" class="modal-overlay" @click="closeModal">
      <div class="modal-content" @click.stop>
        <h2>Agendar Transferência</h2>
        <form @submit.prevent="handleSubmit" class="form-registro">
          <div>
            <label for="nome">Conta de Origem:</label>
            <input type="text" id="conta-origem" v-model="transferencia.source_account" maxlength="10" required />
          </div>
          <div>
            <label for="nome">Conta de Destino:</label>
            <input type="text" id="conta-destino" v-model="transferencia.destination_account" maxlength="10" required />
          </div>
          <div>
            <label for="amount">Valor da transferência:</label>
            <input type="number" id="amount" v-model="transferencia.amount" step=".01" required />
          </div>
          <div>
            <label for="transfer-date">Data da transferência:</label>
            <input type="date" id="transfer-date" v-model="transferencia.transfer_date" required />
          </div>
          <button type="submit" class="btn">Adicionar</button>
          <button type="button" class="btn-cancel" @click="closeModal">Cancelar</button>
        </form>
        <ul v-if="errors.length > 0" class="list-errors">
            <li v-for="(error, index) in errors" :key="index">
                {{ error }}
            </li>
        </ul>
      </div>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent, ref } from 'vue';
  import IError from '../interfaces/IError';
  
  export default defineComponent({
    props: {
      isOpen: {
        type: Boolean,
        required: true
      },
      closeModal: {
        type: Function,
        required: true
      },
      adicionarRegistro: {
        type: Function,
        required: true
      }
    },
    setup(props) {
        const errors = ref([]);
        const cleanAndCloseModal = () => {
            transferencia.value = 
            { 
                source_account: '', 
                destination_account: '', 
                transfer_date: '', 
                amount: 0.0,
                user_id: '1',
                is_active: true
            };
            props.closeModal();
        }

        const showErrors = (e: any) => {
            e.errors.forEach((err: IError) => errors.value.push(err.message));
        }
      
        const transferencia = ref(
        { 
            source_account: '', 
            destination_account: '', 
            transfer_date: '', 
            amount: 0.0,
            user_id: '1',
            is_active: true
        }
      );
  
      const handleSubmit = async () => {
        errors.value = [];
        const response = await props.adicionarRegistro(transferencia.value);
        const json = await response.json();
        if (response.status === 201) {
            cleanAndCloseModal();
        } else {
            showErrors(json);
        }
      };
      return { errors, transferencia, handleSubmit };
    }
  });
  </script>
  
  <style scoped>
  .modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .modal-content {
    background: white;
    width: 800px;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  }
  
  .form-registro {
    padding: 15px;
    background-color: #f0ecc7;
    border-radius: 5px;
  }
  
  .form-registro label {
    display: block;
  }

  .form-registro input {
    padding: 15px;
    border-radius: 5px;
    border: 1px solid #fff;
  }

  form div {
    margin-bottom: 10px;
  }
  
  .btn {
    padding: 10px 20px;
    margin-right: 15px;
    margin-top: 15px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s;
  }

  .btn:hover {
    background-color: #0056b3;
  }
  .btn-cancel:hover {
    text-decoration: underline;
  }

  .list-errors {
    color: red;
    margin-top: 10px;
  }
  </style>
  