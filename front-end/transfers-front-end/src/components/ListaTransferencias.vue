<script lang="ts">
import { ref } from 'vue';
import type ILista from '../interfaces/ILista';
import ModalForm from '../components/ModalForm.vue';
import type ITransferencia from '../interfaces/ITransferencia';
import { obterTransferencias } from '../http';
import { criarTransferencia } from '../http';

export default {
  components: { ModalForm },
  data() {
    const isModalOpen = ref(false);

    const openModal = () => {
      isModalOpen.value = true;
    };

    const closeModal = async () => {
        this.lista = await obterTransferencias();
        isModalOpen.value = false;
    };

    const adicionarRegistro = (registro: ITransferencia): Promise<Response> => {
        return criarTransferencia(registro);
    };
    return {
        lista: {} as ILista,
        isModalOpen,
        openModal, 
        closeModal,
        adicionarRegistro
    };
  },
  async mounted() {
    this.lista = await obterTransferencias();
  }
}
</script>

<template>
  <section>
    <span class="subtitulo-lg sua-lista-texto">
      Transferências agendadas
    </span>
    
    <div class="lista-container">
        <table v-if="lista.total > 0" class="lista-transferencias">
            <thead>
                <th>Conta de Origem</th>
                <th>Conta de Destino</th>
                <th>Valor (R$)</th>
                <th>Taxa (R$)</th>
                <th>Data da transferência</th>
                <th>Data de agendamento</th>
            </thead>
            <tr v-for="(transferencia, index) in lista.items" :key="index">
                <td class="lista-item">
                    <span>{{ transferencia.source_account }}</span>
                </td>
                <td class="lista-item">
                    <span>{{ transferencia.destination_account }}</span>
                </td>
                <td class="lista-item">
                    <span>{{ transferencia.amount }}</span>
                </td>
                <td class="lista-item">
                    <span>{{ transferencia.tax }}</span>
                </td>
                <td class="lista-item">
                    <span>{{ transferencia.transfer_date }}</span>
                </td>
                <td class="lista-item">
                    <span>{{ transferencia.created_at }}</span>
                </td>
            </tr>
        </table>
        <p v-else class="paragrafo lista-vazia">
            Nenhuma transferência agendada
        </p>
    </div>
    <div class="container-add-transferencia">
        <button @click="openModal" class="btn-adicionar">Agendar nova transferência</button>
    </div>
    <ModalForm
      :isOpen="isModalOpen"
      :closeModal="closeModal"
      :adicionarRegistro="adicionarRegistro"
    />
  </section>
</template>

<style scoped>
.lista-texto {
  color: var(--coral, #F0633C);
  display: block;
  text-align: center;
  margin-bottom: 1.5rem;
}

.lista-vazia {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
  gap: 0.25rem;
  color: var(--coral, #F0633C);
  text-align: center;
}

.lista-container {
  width: 900px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.lista-transferencias {
    border-spacing: 15px;
}

.lista-item {
  padding: 10px;
  margin: 5px 5px;
  background-color: #fff;
  border: 2px solid #ddd;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.container-add-transferencia {
  padding: 20px 0;
}

.btn-adicionar {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.btn-adicionar:hover {
  background-color: #0056b3;
}

</style>