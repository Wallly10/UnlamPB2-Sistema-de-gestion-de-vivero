# 🌱 Sistema de Gestión de Vivero

Este proyecto corresponde a un **Trabajo Práctico** desarrollado en la materia *Programación Básica I* en la **Universidad Nacional de La Matanza (UNLaM)**.  
El objetivo es modelar un **sistema de gestión de un vivero**, aplicando conceptos de **POO en Java**: clases, enums, encapsulamiento, arrays y manejo de menús interactivos por consola.

---

## 🛠️ Tecnologías utilizadas
- **Java 17+**
- **JUnit 4** (para pruebas unitarias, si se implementan tests)
- **Eclipse IDE**

---

## 📂 Estructura del proyecto

El proyecto está compuesto por los siguientes elementos principales:

### Dominio
- **`Planta`**  
  Representa una planta del vivero. Contiene atributos como código, tipo, nombre, precio y stock.  
  Incluye el cálculo del **precio final** según el tipo de planta:
  - Hierba/Mata → +20%
  - Arbusto → +60%
  - Árbol → +130%

- **`TipoDePlanta`** *(enum)*  
  Define los tipos de plantas disponibles: `HIERBA`, `MATA`, `ARBUSTO`, `ARBOL`.

- **`MenuPrincipal`** *(enum)*  
  Representa las opciones del menú interactivo por consola:
  1. Agregar planta  
  2. Modificar stock de planta  
  3. Buscar planta por código  
  4. Buscar plantas que contienen una palabra en el nombre  
  5. Vender planta  
  6. Obtener plantas por tipo  
  99. Salir  

- **`Vivero`**  
  Clase que administra la colección de plantas, operaciones de búsqueda, ventas y gestión de stock.

### Interfaz
- **`GestionDeVivero`**  
  Clase principal que contiene el menú de interacción con el usuario por consola.  
  Permite realizar operaciones sobre el vivero mediante las opciones definidas en `MenuPrincipal`.

---

## 🚀 Funcionalidades principales
- Agregar nuevas plantas al vivero.
- Modificar el stock de una planta existente.
- Buscar una planta por su código.
- Buscar plantas por coincidencia en el nombre.
- Vender una planta (reduciendo stock y actualizando saldo del vivero).
- Listar plantas disponibles por tipo.
- Mostrar el precio final de las plantas según su categoría.

---

## 📖 Ejemplo de uso

### 1. Crear una planta
```java
Planta p1 = new Planta(101, TipoDePlanta.ARBOL, "Roble", 500.0, 10);
System.out.println(p1);
