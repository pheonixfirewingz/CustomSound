package io.github.phoenixfirewingz.customsounds.datafixer;

import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;

public class SoundNodeDataFixer extends DataFix {
    public SoundNodeDataFixer(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    @Override
    protected TypeRewriteRule makeRule() {
        return null;
    }
}
