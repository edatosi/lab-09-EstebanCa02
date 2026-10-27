package listas;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Lista simplemente enlazada genérica.
 *
 * INSTRUCCIONES PARA EL ESTUDIANTE
 * --------------------------------
 * 1. Cada método marcado con "TODO" lanza UnsupportedOperationException.
 * Reemplace esa línea por su implementación.
 * 2. NO modifique las pruebas (src/test).
 * 3. Resuelva en orden (Etapa 1, 2, 3...): cada etapa usa las anteriores.
 * 4. Solo se permite usar nodos propios (clase Nodo). No use
 * java.util.LinkedList ni ArrayList dentro de esta clase.
 */
public class ListaEnlazada<T> {

    /** Primer nodo de la lista (null cuando la lista está vacía). */
    private Nodo<T> cabeza;

    /** Cantidad de elementos. Debe mantenerse actualizada en cada operación. */
    private int cantidad;

    public ListaEnlazada() {
        this.cabeza = null;
        this.cantidad = 0;
    }

    // =====================================================================
    // ETAPA 1. Estado de la lista
    // =====================================================================

    /** @return true si la lista no tiene elementos. */
    public boolean estaVacia() {
        return cantidad == 0;
    }

    /** @return número de elementos de la lista. */
    public int tamanio() {
        return cantidad;
    }

    // =====================================================================
    // ETAPA 2. Agregar en los extremos
    // =====================================================================

    /** Inserta el dato como nuevo primer elemento. */
    public void agregarAlInicio(T dato) {
        // TODO Etapa 2: cree un Nodo, enlácelo a la cabeza actual
        // y actualice la cabeza y la cantidad.
        // 1. crear el nodo
        Nodo<T> nuevoNodo = new Nodo<T>(dato);
        // 2. conectar un nuevo nodo a la cabeza
        nuevoNodo.setSiguiente(this.cabeza);
        // 3. cambiar cabeza
        this.cabeza = nuevoNodo;
        this.cantidad++;
    }

    /** Inserta el dato como nuevo último elemento. */
    public void agregarAlFinal(T dato) {
        // TODO Etapa 2: si la lista está vacía es igual que agregarAlInicio;
        // si no, recorra hasta el último nodo y enlace el nuevo.
        Nodo<T> nuevoNodo = new Nodo<T>(dato);

        if (this.cabeza == null) {
            this.cabeza = nuevoNodo;
        } else {
            Nodo<T> actual = this.cabeza;

            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }

            actual.setSiguiente(nuevoNodo);
        }

        this.cantidad++;
    }

    // =====================================================================
    // ETAPA 3. Consultar elementos
    // =====================================================================

    /** @throws NoSuchElementException si la lista está vacía */
    public T obtenerPrimero() {

        // TODO Etapa 3: escriba su código aquí

        if (estaVacia()) {
            throw new NoSuchElementException();
        }
        return cabeza.getDato();
    }

    /** @throws NoSuchElementException si la lista está vacía */
    public T obtenerUltimo() {
        // TODO Etapa 3: escriba su código aquí

        if (estaVacia()) {
            throw new NoSuchElementException();
        }

        Nodo<T> actual = cabeza;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    /**
     * @param indice posición (0 = primer elemento)
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T obtener(int indice) {
        // TODO Etapa 3: valide el índice y recorra la lista 'indice' veces.
        if (indice < 0 || indice >= cantidad) {
            throw new IndexOutOfBoundsException();
        }

        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    // =====================================================================
    // ETAPA 4. Búsqueda
    // =====================================================================

    /** @return true si algún elemento es igual (equals) al dato. Admite null. */
    public boolean contiene(T dato) {
        // TODO Etapa 4: use indiceDe o recorra la lista.
        // Cuidado: para comparar use Objects.equals(a, b).
        return indiceDe(dato) != -1;
    }

    /** @return posición de la primera aparición del dato, o -1 si no está. */
    public int indiceDe(T dato) {
        // TODO Etapa 4: escriba su código aquí
        Nodo<T> actual = cabeza;
        int indice = 0;

        while (actual != null) {
            if (Objects.equals(actual.getDato(), dato)) {
                return indice;
            }
            actual = actual.getSiguiente();
            indice++;
        }

        return -1;
    }

    // =====================================================================
    // ETAPA 5. Insertar y reemplazar en cualquier posición
    // =====================================================================

    /**
     * Inserta el dato en la posición indicada, desplazando los siguientes.
     * Se permite indice == tamanio() (equivale a agregar al final).
     *
     * @throws IndexOutOfBoundsException si indice < 0 o indice > tamanio()
     */
    public void insertar(int indice, T dato) {
        // TODO Etapa 5: casos: indice 0 (inicio), o detenerse en el nodo
        // ANTERIOR a la posición y enlazar allí el nuevo nodo.
        if (indice < 0 || indice > cantidad) {
            throw new IndexOutOfBoundsException();
        }

        if (indice == 0) {
            agregarAlInicio(dato);
            return;
        }

        Nodo<T> anterior = cabeza;
        for (int i = 0; i < indice - 1; i++) {
            anterior = anterior.getSiguiente();
        }

        Nodo<T> nuevoNodo = new Nodo<T>(dato);
        nuevoNodo.setSiguiente(anterior.getSiguiente());
        anterior.setSiguiente(nuevoNodo);
        cantidad++;
    }

    /**
     * Reemplaza el dato de la posición indicada.
     *
     * @return el dato que había antes
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T reemplazar(int indice, T dato) {
        // TODO Etapa 5: escriba su código aquí
        if (indice < 0 || indice >= cantidad) {
            throw new IndexOutOfBoundsException();
        }

        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }

        T datoAnterior = actual.getDato();
        actual.setDato(dato);
        return datoAnterior;
    }

    // =====================================================================
    // ETAPA 6. Eliminar
    // =====================================================================

    /**
     * Elimina y retorna el primer elemento.
     * 
     * @throws NoSuchElementException si la lista está vacía
     */
    public T eliminarPrimero() {
        // TODO Etapa 6: mueva la cabeza al siguiente nodo.
        if (estaVacia()) {
            throw new NoSuchElementException();
        }

        T dato = cabeza.getDato();
        cabeza = cabeza.getSiguiente();
        cantidad--;
        return dato;
    }

    /**
     * Elimina y retorna el último elemento.
     * 
     * @throws NoSuchElementException si la lista está vacía
     */
    public T eliminarUltimo() {
        // TODO Etapa 6: hay que detenerse en el PENULTIMO nodo.
        // Atención al caso de un solo elemento.
        if (estaVacia()) {
            throw new NoSuchElementException();
        }

        if (cantidad == 1) {
            return eliminarPrimero();
        }

        Nodo<T> actual = cabeza;
        while (actual.getSiguiente().getSiguiente() != null) {
            actual = actual.getSiguiente();
        }

        T dato = actual.getSiguiente().getDato();
        actual.setSiguiente(null);
        cantidad--;
        return dato;
    }

    /**
     * Elimina y retorna el elemento de la posición indicada.
     * 
     * @throws IndexOutOfBoundsException si indice < 0 o indice >= tamanio()
     */
    public T eliminar(int indice) {
        // TODO Etapa 6: escriba su código aquí
        if (indice < 0 || indice >= cantidad) {
            throw new IndexOutOfBoundsException();
        }

        if (indice == 0) {
            return eliminarPrimero();
        }

        Nodo<T> anterior = cabeza;
        for (int i = 0; i < indice - 1; i++) {
            anterior = anterior.getSiguiente();
        }

        T dato = anterior.getSiguiente().getDato();
        anterior.setSiguiente(anterior.getSiguiente().getSiguiente());
        cantidad--;
        return dato;
    }

    /**
     * Elimina la primera aparición del dato.
     * 
     * @return true si se eliminó algo, false si el dato no estaba
     */
    public boolean eliminarValor(T dato) {
        // TODO Etapa 6: puede apoyarse en indiceDe y eliminar(int).
        int indice = indiceDe(dato);
        if (indice == -1) {
            return false;
        }

        eliminar(indice);
        return true;
    }

    /** Deja la lista vacía. */
    public void vaciar() {
        // TODO Etapa 6: escriba su código aquí
        cabeza = null;
        cantidad = 0;
    }

    // =====================================================================
    // ETAPA 7. Representación en texto
    // =====================================================================

    /**
     * Formato: "[1, 2, 3]". Lista vacía: "[]".
     * Un elemento null se imprime como "null".
     */
    @Override
    public String toString() {
        // TODO Etapa 7: recorra la lista con un StringBuilder.
        String resultado = "[";
        Nodo<T> actual = cabeza;

        while (actual != null) {
            resultado += actual.getDato();
            actual = actual.getSiguiente();

            if (actual != null) {
                resultado += ", ";
            }
        }

        return resultado + "]";
    }

    // =====================================================================
    // ETAPA 8. Retos
    // =====================================================================

    /**
     * Invierte la lista EN SITIO: no cree nodos nuevos, solo cambie
     * las referencias 'siguiente' de los nodos existentes.
     */
    public void invertir() {
        // TODO Etapa 8: use tres referencias: anterior, actual, siguiente.
        Nodo<T> anterior = null;
        Nodo<T> actual = cabeza;

        while (actual != null) {
            Nodo<T> siguiente = actual.getSiguiente();
            actual.setSiguiente(anterior);
            anterior = actual;
            actual = siguiente;
        }

        cabeza = anterior;
    }

    /**
     * Retorna el elemento del medio. Si el tamaño es par, el segundo de los
     * dos centrales (ej.: [1,2,3,4] -> 3). Pista: dos punteros, uno avanza
     * de a 1 y otro de a 2.
     *
     * @throws NoSuchElementException si la lista está vacía
     */
    public T obtenerMedio() {
        // TODO Etapa 8: escriba su código aquí
        if (estaVacia()) {
            throw new NoSuchElementException();
        }

        Nodo<T> lento = cabeza;
        Nodo<T> rapido = cabeza;

        while (rapido != null && rapido.getSiguiente() != null) {
            lento = lento.getSiguiente();
            rapido = rapido.getSiguiente().getSiguiente();
        }

        return lento.getDato();

    }

    /**
     * Elimina los elementos repetidos dejando solo la primera aparición
     * de cada uno, conservando el orden. Ej.: [1,2,1,3,2] -> [1,2,3]
     */
    public void eliminarDuplicados() {
        Nodo<T> actual = cabeza;

        while (actual != null) {
            Nodo<T> anterior = actual;
            Nodo<T> revisar = actual.getSiguiente();

            while (revisar != null) {
                if (java.util.Objects.equals(actual.getDato(), revisar.getDato())) {
                    anterior.setSiguiente(revisar.getSiguiente());
                    cantidad--;
                    revisar = anterior.getSiguiente();
                } else {
                    anterior = revisar;
                    revisar = revisar.getSiguiente();
                }
            }

            actual = actual.getSiguiente();
        }
    }
}
