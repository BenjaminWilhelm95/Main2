import java.util.ArrayList;
import java.util.List;

class Animal {
    private String nombre;
    private double peso;
    private String sexo;

    public Animal(String nombre, double peso, String sexo) {
        this.nombre = nombre;
        this.peso = peso;
        this.sexo = sexo;
    }

    public void hacerSonido() {
        // Por defecto, no se especifica un sonido para el animal genérico
        System.out.println("El animal hace un sonido.");
    }

    // Getters y setters para los atributos

    @Override
    public String toString() {
        return "Animal: " + nombre + " - Peso: " + peso + " - Sexo: " + sexo;
    }
}

class Perro extends Animal {
    public Perro(String nombre, double peso, String sexo) {
        super(nombre, peso, sexo);
    }

    @Override
    public void hacerSonido() {
        System.out.println("El perro hace guau guau.");
    }
}

class Gato extends Animal {
    public Gato(String nombre, double peso, String sexo) {
        super(nombre, peso, sexo);
    }

    @Override
    public void hacerSonido() {
        System.out.println("El gato hace miau miau.");
    }
}

class Persona {
    private String nombre;
    private int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }
}

class Cliente extends Persona {
    public Cliente(String nombre, int edad) {
        super(nombre, edad);
    }

    @Override
    public String toString() {
        return "Cliente: " + getNombre();
    }
}

class Vendedor extends Persona {
    private String rut;

    public Vendedor(String nombre, int edad, String rut) {
        super(nombre, edad);
        this.rut = rut;
    }

    public String getRut() {
        return rut;
    }

    @Override
    public String toString() {
        return "Vendedor: " + getNombre();
    }
}

class AgenciaDeViajes {
    private List<Cliente> clientes;
    private List<Vendedor> vendedores;
    private List<Animal> animales;

    public AgenciaDeViajes() {
        clientes = new ArrayList<>();
        vendedores = new ArrayList<>();
        animales = new ArrayList<>();
    }

    public void añadirCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void añadirVendedor(Vendedor vendedor) {
        vendedores.add(vendedor);
    }

    public void añadirAnimal(Animal animal) {
        animales.add(animal);
    }

    public Cliente buscarCliente(String nombreCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equalsIgnoreCase(nombreCliente)) {
                return cliente;
            }
        }
        return null; // Si no se encuentra el cliente
    }

    public Vendedor buscarVendedor(String nombreVendedor) {
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getNombre().equalsIgnoreCase(nombreVendedor)) {
                return vendedor;
            }
        }
        return null; // Si no se encuentra el vendedor
    }

    public void eliminarVendedor(Vendedor vendedor) {
        vendedores.remove(vendedor);
    }

    public void generarVenta(Cliente cliente, Vendedor vendedor) {
        // Implementa la lógica para generar una venta
        System.out.println("Se generó una venta para el cliente " + cliente.getNombre() + " con el vendedor " + vendedor.getNombre());
    }

    public void hacerSonidoAnimal(Animal animal) {
        animal.hacerSonido();
    }

    public List<Persona> buscarPersonasMayoresDe30() {
        List<Persona> personasMayores = new ArrayList<>();
        for (Cliente cliente : clientes) {
            if (cliente.getEdad() > 30) {
                personasMayores.add(cliente);
            }
        }
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getEdad() > 30) {
                personasMayores.add(vendedor);
            }
        }
        return personasMayores;
    }

    public int contarClientes() {
        return clientes.size();
    }

    public int contarVendedores() {
        return vendedores.size();
    }

    public void despedirVendedor(String rut) {
        Vendedor vendedor = buscarVendedorPorRut(rut);
        if (vendedor != null) {
            eliminarVendedor(vendedor);
            System.out.println("Se ha despedido al vendedor con RUT: " + rut);
        } else {
            System.out.println("No se encontró un vendedor con el RUT especificado.");
        }
    }

    private Vendedor buscarVendedorPorRut(String rut) {
        for (Vendedor vendedor : vendedores) {
            if (vendedor.getRut().equals(rut)) {
                return vendedor;
            }
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        AgenciaDeViajes agencia = new AgenciaDeViajes();

        Cliente cliente1 = new Cliente("Juan", 35);
        Cliente cliente2 = new Cliente("María", 28);
        agencia.añadirCliente(cliente1);
        agencia.añadirCliente(cliente2);

        Vendedor vendedor1 = new Vendedor("Pedro", 40, "12345678-9");
        Vendedor vendedor2 = new Vendedor("Laura", 25, "98765432-1");
        agencia.añadirVendedor(vendedor1);
        agencia.añadirVendedor(vendedor2);

        Perro perro = new Perro("Max", 15.5, "Macho");
        Gato gato = new Gato("Luna", 7.2, "Hembra");
        agencia.añadirAnimal(perro);
        agencia.añadirAnimal(gato);

        Cliente clienteEncontrado = agencia.buscarCliente("Juan");
        Vendedor vendedorEncontrado = agencia.buscarVendedor("Laura");

        System.out.println(clienteEncontrado);
        System.out.println(vendedorEncontrado);

        agencia.eliminarVendedor(vendedor1);

        agencia.generarVenta(cliente2, vendedorEncontrado);

        agencia.hacerSonidoAnimal(perro);
        agencia.hacerSonidoAnimal(gato);

        List<Persona> personasMayores = agencia.buscarPersonasMayoresDe30();
        System.out.println("Personas mayores de 30 años:");
        for (Persona persona : personasMayores) {
            System.out.println(persona.getNombre());
        }

        int cantidadClientes = agencia.contarClientes();
        System.out.println("Cantidad de clientes: " + cantidadClientes);

        int cantidadVendedores = agencia.contarVendedores();
        System.out.println("Cantidad de vendedores: " + cantidadVendedores);

        agencia.despedirVendedor("12345678-9");
    }
}
