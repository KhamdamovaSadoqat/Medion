package uz.medion.ui.main.user.aboutDoctor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.AboutDoctorSertificateItem
import uz.medion.databinding.ItemSertificateBinding
import uz.medion.utils.ImageDownloader

class AboutDoctorSertificateAdapter(private val itemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<AboutDoctorSertificateAdapter.VH>() {

    private var listItem = listOf<AboutDoctorSertificateItem>()

    fun setData(listItem: List<AboutDoctorSertificateItem>) {
        this.listItem = listItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemSertificateBinding>(
                inflater,
                R.layout.item_sertificate,
                parent,
                false
            )
        return VH(binding, parent.context)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(position)
        }
        holder.onBind(listItem[position])
    }

    override fun getItemCount() = listItem.size

    class VH(private val binding: ItemSertificateBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(certificate: AboutDoctorSertificateItem) {
            binding.apply {
                if (!certificate.image.isNullOrEmpty()) {
                    certificate.image.let {
                        ImageDownloader.loadImage(
                            context,
                            it,
                            binding.ivCertificate
                        )
                    }
                }
            }

        }
    }
}