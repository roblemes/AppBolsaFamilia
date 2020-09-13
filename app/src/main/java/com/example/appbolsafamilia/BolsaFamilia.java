package com.example.appbolsafamilia;

public class BolsaFamilia {
    private String nomeMunicipio, estado, estadoNome;
    private int beneficiarios;
    private double totalPago;

    public BolsaFamilia() {
    }

    public BolsaFamilia(String nomeMunicipio, String estado, String estadoNome, int beneficiarios, double totalPago) {
        this.nomeMunicipio = nomeMunicipio;
        this.estado = estado;
        this.estadoNome = estadoNome;
        this.beneficiarios = beneficiarios;
        this.totalPago = totalPago;
    }

    public String getEstadoNome() {
        return estadoNome;
    }

    public void setEstadoNome(String estadoNome) {
        this.estadoNome = estadoNome;
    }

    public int getBeneficiarios() {
        return beneficiarios;
    }

    public void setBeneficiarios(int beneficiarios) {
        this.beneficiarios = beneficiarios;
    }

    public double getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(double totalPago) {
        this.totalPago = totalPago;
    }

    public String getNomeMunicipio() {
        return nomeMunicipio;
    }

    public void setNomeMunicipio(String nomeMunicipio) {
        this.nomeMunicipio = nomeMunicipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Municipio:" + nomeMunicipio + '\n' +
                //"Nome do estado:" + estadoNome + '\n' +
                "Estado:" + estado + '\n' +
                "Beneficiarios:" + beneficiarios + '\n' +
                "Total pago:" + totalPago + '\n' +
                "------------------" + '\n'
                ;
    }
}
