package izuanqian.user.dbo

import izuanqian.amap.GaoDeDiTuClient
import java.util.*

data class DboGaoDeDiTuPoi(val poi: GaoDeDiTuClient.Vo.Poi) {

    val id: String = poi.id
    val title: String = poi.name
    val tel: String = poi.tel
    val lng: String = poi.longitude
    val lat: String = poi.latitude
    val address: String = poi.address
    var logo: String? = null

    init {

        if (!Objects.isNull(poi.domain_list) && poi.domain_list.size >= 6) {
            this.logo = poi.domain_list[5].value
        }

    }
}