import type ITransferencia from "./ITransferencia";

export default interface ILista {
    currentPage: number;
    perPage: number;
    total: number;
    items: ITransferencia[];
}