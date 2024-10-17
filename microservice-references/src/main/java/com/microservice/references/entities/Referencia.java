package com.microservice.references.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "referencias")
public class Referencia {
    @Id
    @Size(max = 50)
    @Nationalized
    @Column(name = "COD_MRI", nullable = false, length = 50)
    private String codMri;

    @Column(name = "CATEGORIA_MRI", columnDefinition = "tinyint not null")
    private Short categoriaMri;

    @Column(name = "SUBCATEGORIA_MRI", columnDefinition = "tinyint not null")
    private Short subcategoriaMri;

    @Column(name = "LINEA_MRI", columnDefinition = "tinyint not null")
    private Short lineaMri;

    @NotNull
    @Column(name = "DESCOD_MRI", nullable = false)
    private String descodMri;

    @Column(name = "REFP1_MRI")
    private String refp1Mri;

    @Column(name = "REFP2_MRI")
    private String refp2Mri;

    @Column(name = "DESADI_MRI")
    private String desadiMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "UNI_MRI", length = 50)
    private String uniMri;

    @Column(name = "COSTO_MRI")
    private Double costoMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "DTOBAS_MRI", length = 50)
    private String dtobasMri;

    @Column(name = "PVTA_MRI")
    private Integer pvtaMri;

    @Column(name = "PVTA1_MRI")
    private Integer pvta1Mri;

    @Column(name = "PVTA2_MRI")
    private Integer pvta2Mri;


    @Column(name = "IVA_MRI")
    private String ivaMri;

    @Column(name = "IVACOM_MRI")
    private String ivacomMri;

    @Column(name = "COSDOL_MRI")
    private Double cosdolMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "TIPOPROD_MRI", length = 50)
    private String tipoprodMri;

    @Column(name = "ULTCOS_MRI")
    private String ultcosMri;

    @Column(name = "EMPAQ_MRI")
    private String empaqMri;

    @Column(name = "PESO_MRI")
    private Double pesoMri;

    @Column(name = "ALTO_MRI")
    private Double altoMri;

    @Column(name = "ANCHO_MRI")
    private Double anchoMri;

    @Column(name = "LARGO_MRI")
    private Double largoMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "ANOENT_MRI", length = 50)
    private String anoentMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "MESENT_MRI", length = 50)
    private String mesentMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "DIAENT_MRI", length = 50)
    private String diaentMri;

    @Size(max = 50)
    @Column(name = "CLAVE_MRI", length = 50)
    private String claveMri;

    @Column(name = "IDE1_MRI", columnDefinition = "tinyint")
    private Short ide1Mri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "IDE2_MRI", length = 50)
    private String ide2Mri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "ENTGAN_MRI", length = 50)
    private String entganMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "SEGURI_MRI", length = 50)
    private String seguriMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "IDEREP_MRI", length = 50)
    private String iderepMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "TICKET_MRI", length = 50)
    private String ticketMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "PLANCONMET_MRI", length = 50)
    private String planconmetMri;

    @Column(name = "RIFA_MRI")
    private Boolean rifaMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "SALDOIMPERFECTO_MRI", length = 50)
    private String saldoimperfectoMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "UNIMEDIDA_MRI", length = 50)
    private String unimedidaMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "VALCOMISION_MRI", length = 50)
    private String valcomisionMri;

    @Column(name = "EANCEN_MRI")
    private Double eancenMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "COMBO_MRI", length = 50)
    private String comboMri;

    @Column(name = "UNIMASTER_MRI")
    private Integer unimasterMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "INDCOD_MRI", length = 50)
    private String indcodMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "TIPMED_MRI", length = 50)
    private String tipmedMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "INDLIQ_MRI", length = 50)
    private String indliqMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "ULTEXI_MRI", length = 50)
    private String ultexiMri;

    @Column(name = "UNICEDI_MRI")
    private String unicediMri;

    @Column(name = "PREORIG_MRI")
    private Integer preorigMri;

    @Column(name = "PROVEED_MRI")
    private String proveedMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "PROVOLUM_MRI", length = 50)
    private String provolumMri;

    @Column(name = "PAISORI_MRI")
    private Short paisoriMri;

    @Column(name = "PAISCOM_MRI")
    private Short paiscomMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "PROMOESP_MRI", length = 50)
    private String promoespMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "TIPMEDEMPAQ_MRI", length = 50)
    private String tipmedempaqMri;

    @Column(name = "ALTOEMPAQ_MRI", columnDefinition = "tinyint")
    private Short altoempaqMri;

    @Column(name = "ANCHEMPAQ_MRI")
    private Double anchempaqMri;

    @Column(name = "LARGEMPAQ_MRI")
    private Double largempaqMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "TIPMEDMASTER_MRI", length = 50)
    private String tipmedmasterMri;

    @Column(name = "ALTOMASTER_MRI")
    private Double altomasterMri;

    @Column(name = "ANCHMASTER_MRI")
    private Double anchmasterMri;

    @Column(name = "LARGMASTER_MRI")
    private Double largmasterMri;

    @Column(name = "SUCPRO_MRI", columnDefinition = "tinyint")
    private Short sucproMri;

    @Column(name = "INDKARDEX_MRI", columnDefinition = "tinyint")
    private Short indkardexMri;

    @Column(name = "INANDOUT_MRI", columnDefinition = "tinyint")
    private Short inandoutMri;

    @Column(name = "INDCOSTO_MRI", columnDefinition = "tinyint")
    private Short indcostoMri;

    @Column(name = "CODGRUPO_MRI", columnDefinition = "tinyint")
    private Short codgrupoMri;

    @Column(name = "TIPOLO_MRI", columnDefinition = "tinyint")
    private Short tipoloMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "CUABAS_MRI", length = 50)
    private String cuabasMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "TIPMPROPIA_MRI", length = 50)
    private String tipmpropiaMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "SUSPEND_MRI", length = 50)
    private String suspendMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "EXHIBI_MRI", length = 50)
    private String exhibiMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "EXCLUIDO_MRI", length = 50)
    private String excluidoMri;

    @Column(name = "VLIMPCOMPRA_MRI", columnDefinition = "tinyint")
    private Short vlimpcompraMri;

    @Size(max = 50)
    @Nationalized
    @Column(name = "TIPOPESO_MRI", length = 50)
    private String tipopesoMri;

    @Column(name = "PESOMED_MRI")
    private String pesomedMri;

}