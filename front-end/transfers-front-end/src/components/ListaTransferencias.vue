<script lang="ts">
import type { PropType } from 'vue';
import ILista from '../interfaces/ILista';

export default {
  components: {  },
  props: {
    lista: { type: Array as PropType<ILista[]>, required: true }
  },
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
                <th>Conta Origem</th>
                <th>Conta Destino</th>
                <th>Valor</th>
                <th>Taxa</th>
                <th>Data da transferência</th>
                <th>Data de agendamento</th>
            </thead>
            <tr v-for="transferencia in lista.items" :key="transferencia">
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
                    <span>{{ }}</span>
                </td>
                <td class="lista-item">
                    <span>{{ transferencia.transfer_date }}</span>
                </td>
                <td class="lista-item">
                    <span>{{  }}</span>
                </td>
            </tr>
        </table>
        <p v-else class="paragrafo lista-vazia">
            Não existem transferências
        </p>
    </div>
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

.lista-item {
  padding: 10px;
  margin: 5px 5px;
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 4px;
  transition: background-color 0.3s;
}

.lista-item:hover {
  background-color: #f1f1f1;
}
</style>