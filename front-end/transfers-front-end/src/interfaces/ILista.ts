import type ITransferencia from "./ITransferencia";

export default interface ILista extends Function {
    currentPage: number;
    perPage: number;
    total: number;
    items: ITransferencia[];
}