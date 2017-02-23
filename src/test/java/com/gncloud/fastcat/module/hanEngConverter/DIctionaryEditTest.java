package com.gncloud.fastcat.module.hanEngConverter;

import net.sf.extjwnl.data.*;
import net.sf.extjwnl.dictionary.Dictionary;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by gncloud on 2017-02-23.
 */
public class DIctionaryEditTest {
    @Test
    public void addOneWord() throws Exception{

        String keyword = "gncloud";
        Dictionary dictionary = DicRepository.getInstance("./dic/custom.noun.txt").getWordnetDictionary();
        Iterator<Synset> synsets = dictionary.getSynsetIterator(POS.NOUN);

        dictionary.edit();
        Synset newSynset = new Synset(dictionary, POS.NOUN);
        IndexWord newWord = new IndexWord(dictionary, keyword, POS.NOUN, newSynset);
        Synset topmostSynset = synsets.next();
        Pointer newPointer = new Pointer(PointerType.HYPONYM, topmostSynset, newSynset);
        topmostSynset.getPointers().add(newPointer);
        dictionary.save();
        assertTrue(dictionary.getIndexWord(POS.NOUN, keyword) != null);
    }


    @Test
    public void addOneWordWithoutSynset() throws Exception{

        String keyword = "gncloud2";
        Dictionary dictionary = DicRepository.getInstance("./dic/custom.noun.txt").getWordnetDictionary();
//        Iterator<Synset> synsets = dictionary.getSynsetIterator(POS.NOUN);
        dictionary.edit();
        Synset newSynset = new Synset(dictionary, POS.NOUN);
        IndexWord newWord = new IndexWord(dictionary, keyword, POS.NOUN, newSynset);
//        dictionary.save();
        assertTrue(dictionary.getIndexWord(POS.NOUN, keyword) != null);

    }


}
