package cz.juzna.latte.psi.impl;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import cz.juzna.latte.LatteLanguage;
import cz.juzna.latte.file.LatteFileType;
import cz.juzna.latte.psi.LatteFile;
import org.jetbrains.annotations.NotNull;

public class LatteFileImpl extends PsiFileBase implements LatteFile {
    public LatteFileImpl(FileViewProvider viewProvider) {
        super(viewProvider, LatteLanguage.LATTE_LANGUAGE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return LatteFileType.LATTE_FILE_TYPE;
    }

    @Override
    public String toString() {
        return "LatteFile:" + getName();
    }
}
