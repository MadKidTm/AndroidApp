package com.gsb.alexa.gsbappli.Classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gsb.alexa.gsbappli.R;

/**
 * Created by Alexa on 21/03/2018.
 */

public class RepertoireAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    String[] mListe;

    static class ViewHolder{
        public TextView mNom ;
        public TextView mNumero ;
    }

    @Override
    public int getCount() {
        return 0;
    }

    /**
     * Récupérer un item de la lite en foction de sa position
     * @param position - Position de l'item a récupérer
     * @return l'item récupéré
     */
    public Object getItem(int position){

        return position;
    }

    /**
     * Récupérer l'identifiant d'un item de la liste en fonction de sa position
     * @param position - Position de l'item a récupérer
     * @return l'identifiant de l'item
     */
    public long getItemId(int position){

        return position;
    }

    /**
     * appelé a chaque fois qu'un item est affiché a l'écran
     * @param position - position de l'item dans la liste (donc l'adapter)
     * @param convertView - valeur de la vue qui disparait de l'ecran
     * @param parent - layout rataché a la vue
     * @return
     */
    public View getView( int position, View convertView, ViewGroup parent){
        ViewHolder holder = null;

        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.repertoire_item, null);

            holder = new ViewHolder();

            //holder.mNom = (TextView) convertView.findViewById(R.id.nom);
            //holder.mNumero = (TextView) convertView.findViewById(R.id.numero);




            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.mNom.setText("test");
        holder.mNumero.setText("test0657353");

        return convertView;





    }


}
