PGDMP                  
    |            bibliotecanova    17.2    17.2 6    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                           false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                           false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                           false            �           1262    16509    bibliotecanova    DATABASE     �   CREATE DATABASE bibliotecanova WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE bibliotecanova;
                     postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                     pg_database_owner    false            �           0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                        pg_database_owner    false    4            �            1259    16641    alunos    TABLE     	  CREATE TABLE public.alunos (
    id integer NOT NULL,
    nome character varying(100) NOT NULL,
    sobrenome character varying(100) NOT NULL,
    data_nascimento date NOT NULL,
    rg character varying(20) NOT NULL,
    matricula character varying(20) NOT NULL
);
    DROP TABLE public.alunos;
       public         heap r       postgres    false    4            �            1259    16640    alunos_id_seq    SEQUENCE     �   CREATE SEQUENCE public.alunos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.alunos_id_seq;
       public               postgres    false    228    4            �           0    0    alunos_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.alunos_id_seq OWNED BY public.alunos.id;
          public               postgres    false    227            �            1259    16563    autores    TABLE     �   CREATE TABLE public.autores (
    id integer NOT NULL,
    nome character varying(100) NOT NULL,
    sobrenome character varying(100) NOT NULL
);
    DROP TABLE public.autores;
       public         heap r       postgres    false    4            �            1259    16562    autores_id_seq    SEQUENCE     �   CREATE SEQUENCE public.autores_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.autores_id_seq;
       public               postgres    false    4    220            �           0    0    autores_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.autores_id_seq OWNED BY public.autores.id;
          public               postgres    false    219            �            1259    16511    editora    TABLE     �   CREATE TABLE public.editora (
    id integer NOT NULL,
    nome character varying(100) NOT NULL,
    nacional boolean NOT NULL
);
    DROP TABLE public.editora;
       public         heap r       postgres    false    4            �            1259    16510    editora_id_seq    SEQUENCE     �   CREATE SEQUENCE public.editora_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.editora_id_seq;
       public               postgres    false    218    4            �           0    0    editora_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.editora_id_seq OWNED BY public.editora.id;
          public               postgres    false    217            �            1259    16611    emprestimos    TABLE     2  CREATE TABLE public.emprestimos (
    id integer NOT NULL,
    pessoa_nome character varying(100) NOT NULL,
    pessoa_telefone character varying(15),
    pessoa_cpf character varying(11) NOT NULL,
    data_emprestimo date NOT NULL,
    material_id integer NOT NULL,
    devolvido boolean DEFAULT false
);
    DROP TABLE public.emprestimos;
       public         heap r       postgres    false    4            �            1259    16610    emprestimos_id_seq    SEQUENCE     �   CREATE SEQUENCE public.emprestimos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.emprestimos_id_seq;
       public               postgres    false    224    4            �           0    0    emprestimos_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.emprestimos_id_seq OWNED BY public.emprestimos.id;
          public               postgres    false    223            �            1259    16593    livros    TABLE     \  CREATE TABLE public.livros (
    id integer NOT NULL,
    titulo character varying(150) NOT NULL,
    ano integer NOT NULL,
    edicao character varying(50) NOT NULL,
    estante integer NOT NULL,
    exemplares_totais integer NOT NULL,
    exemplares_disponiveis integer NOT NULL,
    autor_id integer NOT NULL,
    editora_id integer NOT NULL
);
    DROP TABLE public.livros;
       public         heap r       postgres    false    4            �            1259    16592    livros_id_seq    SEQUENCE     �   CREATE SEQUENCE public.livros_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.livros_id_seq;
       public               postgres    false    222    4            �           0    0    livros_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.livros_id_seq OWNED BY public.livros.id;
          public               postgres    false    221            �            1259    16624 
   periodicos    TABLE     :  CREATE TABLE public.periodicos (
    issn integer NOT NULL,
    titulo character varying(150) NOT NULL,
    volume integer NOT NULL,
    estante integer NOT NULL,
    exemplares_totais integer NOT NULL,
    exemplares_disponiveis integer NOT NULL,
    autor_id integer NOT NULL,
    editora_id integer NOT NULL
);
    DROP TABLE public.periodicos;
       public         heap r       postgres    false    4            �            1259    16623    periodicos_issn_seq    SEQUENCE     �   CREATE SEQUENCE public.periodicos_issn_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.periodicos_issn_seq;
       public               postgres    false    4    226            �           0    0    periodicos_issn_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.periodicos_issn_seq OWNED BY public.periodicos.issn;
          public               postgres    false    225            @           2604    16644 	   alunos id    DEFAULT     f   ALTER TABLE ONLY public.alunos ALTER COLUMN id SET DEFAULT nextval('public.alunos_id_seq'::regclass);
 8   ALTER TABLE public.alunos ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    228    227    228            ;           2604    16566 
   autores id    DEFAULT     h   ALTER TABLE ONLY public.autores ALTER COLUMN id SET DEFAULT nextval('public.autores_id_seq'::regclass);
 9   ALTER TABLE public.autores ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    219    220    220            :           2604    16514 
   editora id    DEFAULT     h   ALTER TABLE ONLY public.editora ALTER COLUMN id SET DEFAULT nextval('public.editora_id_seq'::regclass);
 9   ALTER TABLE public.editora ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    217    218    218            =           2604    16614    emprestimos id    DEFAULT     p   ALTER TABLE ONLY public.emprestimos ALTER COLUMN id SET DEFAULT nextval('public.emprestimos_id_seq'::regclass);
 =   ALTER TABLE public.emprestimos ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    224    223    224            <           2604    16596 	   livros id    DEFAULT     f   ALTER TABLE ONLY public.livros ALTER COLUMN id SET DEFAULT nextval('public.livros_id_seq'::regclass);
 8   ALTER TABLE public.livros ALTER COLUMN id DROP DEFAULT;
       public               postgres    false    222    221    222            ?           2604    16627    periodicos issn    DEFAULT     r   ALTER TABLE ONLY public.periodicos ALTER COLUMN issn SET DEFAULT nextval('public.periodicos_issn_seq'::regclass);
 >   ALTER TABLE public.periodicos ALTER COLUMN issn DROP DEFAULT;
       public               postgres    false    225    226    226            �          0    16641    alunos 
   TABLE DATA           U   COPY public.alunos (id, nome, sobrenome, data_nascimento, rg, matricula) FROM stdin;
    public               postgres    false    228   �>       �          0    16563    autores 
   TABLE DATA           6   COPY public.autores (id, nome, sobrenome) FROM stdin;
    public               postgres    false    220   ?       �          0    16511    editora 
   TABLE DATA           5   COPY public.editora (id, nome, nacional) FROM stdin;
    public               postgres    false    218   8?       �          0    16611    emprestimos 
   TABLE DATA           |   COPY public.emprestimos (id, pessoa_nome, pessoa_telefone, pessoa_cpf, data_emprestimo, material_id, devolvido) FROM stdin;
    public               postgres    false    224   x?       �          0    16593    livros 
   TABLE DATA           �   COPY public.livros (id, titulo, ano, edicao, estante, exemplares_totais, exemplares_disponiveis, autor_id, editora_id) FROM stdin;
    public               postgres    false    222   �?       �          0    16624 
   periodicos 
   TABLE DATA           �   COPY public.periodicos (issn, titulo, volume, estante, exemplares_totais, exemplares_disponiveis, autor_id, editora_id) FROM stdin;
    public               postgres    false    226   l@       �           0    0    alunos_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.alunos_id_seq', 2, true);
          public               postgres    false    227            �           0    0    autores_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.autores_id_seq', 2, true);
          public               postgres    false    219                        0    0    editora_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.editora_id_seq', 3, true);
          public               postgres    false    217                       0    0    emprestimos_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.emprestimos_id_seq', 4, true);
          public               postgres    false    223                       0    0    livros_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.livros_id_seq', 5, true);
          public               postgres    false    221                       0    0    periodicos_issn_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.periodicos_issn_seq', 1, true);
          public               postgres    false    225            L           2606    16648    alunos alunos_matricula_key 
   CONSTRAINT     [   ALTER TABLE ONLY public.alunos
    ADD CONSTRAINT alunos_matricula_key UNIQUE (matricula);
 E   ALTER TABLE ONLY public.alunos DROP CONSTRAINT alunos_matricula_key;
       public                 postgres    false    228            N           2606    16646    alunos alunos_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.alunos
    ADD CONSTRAINT alunos_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.alunos DROP CONSTRAINT alunos_pkey;
       public                 postgres    false    228            D           2606    16568    autores autores_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.autores
    ADD CONSTRAINT autores_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.autores DROP CONSTRAINT autores_pkey;
       public                 postgres    false    220            B           2606    16516    editora editora_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.editora
    ADD CONSTRAINT editora_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.editora DROP CONSTRAINT editora_pkey;
       public                 postgres    false    218            H           2606    16617    emprestimos emprestimos_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.emprestimos
    ADD CONSTRAINT emprestimos_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.emprestimos DROP CONSTRAINT emprestimos_pkey;
       public                 postgres    false    224            F           2606    16598    livros livros_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.livros
    ADD CONSTRAINT livros_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.livros DROP CONSTRAINT livros_pkey;
       public                 postgres    false    222            J           2606    16629    periodicos periodicos_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.periodicos
    ADD CONSTRAINT periodicos_pkey PRIMARY KEY (issn);
 D   ALTER TABLE ONLY public.periodicos DROP CONSTRAINT periodicos_pkey;
       public                 postgres    false    226            Q           2606    16618 (   emprestimos emprestimos_material_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.emprestimos
    ADD CONSTRAINT emprestimos_material_id_fkey FOREIGN KEY (material_id) REFERENCES public.livros(id);
 R   ALTER TABLE ONLY public.emprestimos DROP CONSTRAINT emprestimos_material_id_fkey;
       public               postgres    false    224    222    4678            O           2606    16599    livros livros_autor_id_fkey    FK CONSTRAINT     }   ALTER TABLE ONLY public.livros
    ADD CONSTRAINT livros_autor_id_fkey FOREIGN KEY (autor_id) REFERENCES public.autores(id);
 E   ALTER TABLE ONLY public.livros DROP CONSTRAINT livros_autor_id_fkey;
       public               postgres    false    222    4676    220            P           2606    16604    livros livros_editora_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.livros
    ADD CONSTRAINT livros_editora_id_fkey FOREIGN KEY (editora_id) REFERENCES public.editora(id);
 G   ALTER TABLE ONLY public.livros DROP CONSTRAINT livros_editora_id_fkey;
       public               postgres    false    4674    222    218            R           2606    16630 #   periodicos periodicos_autor_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.periodicos
    ADD CONSTRAINT periodicos_autor_id_fkey FOREIGN KEY (autor_id) REFERENCES public.autores(id);
 M   ALTER TABLE ONLY public.periodicos DROP CONSTRAINT periodicos_autor_id_fkey;
       public               postgres    false    226    4676    220            S           2606    16635 %   periodicos periodicos_editora_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.periodicos
    ADD CONSTRAINT periodicos_editora_id_fkey FOREIGN KEY (editora_id) REFERENCES public.editora(id);
 O   ALTER TABLE ONLY public.periodicos DROP CONSTRAINT periodicos_editora_id_fkey;
       public               postgres    false    218    4674    226            �   j   x�%��	�@E�d�[�$��'+��&hcc�s#Gq1Ol���3�v��/BD�R�,Is���%b��=��z�6/a���?K$��wZ�5C]\D|�      �   '   x�3�t,�L���.M,.N�K�2�tL) �b���� ,��      �   0   x�3�.H,N-�,�2���Q��/(��K�L�2��J����qqq ��
�      �   Y   x�3���?�8�����������̐����������Ԁ����D��P�ȒӐ��˘�'5?/�(%_��(/?'I�B�1�Fc�4�=... }��      �   {   x�3�t�L���L�4200�A#0i�e���XZ��������[��������ihii�i�i�i�i R�e����Y�R�Z�Sd�i�iT
2͔���G�(�$e�j60�41 K��qqq ̓�      �   %   x�3���/�K��44�4�46�42�4�4����� _�N     